package io.github.cartoes.mscreditappraiser.service;

import feign.FeignException;
import io.github.cartoes.mscreditappraiser.exception.ClientDataNotFoundException;
import io.github.cartoes.mscreditappraiser.exception.ErrorComunicationMicroServicesException;
import io.github.cartoes.mscreditappraiser.feignClients.CardResourceFeign;
import io.github.cartoes.mscreditappraiser.feignClients.ClientResourceFeign;
import io.github.cartoes.mscreditappraiser.model.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SituationCreditService {

    private final ClientResourceFeign clientResourceFeign;
    private final CardResourceFeign cardResourceFeign;

    public SituationCredit getSituationClient(String cpf) throws ClientDataNotFoundException,
            ErrorComunicationMicroServicesException {

        try {

            ResponseEntity<ClientData> clientResponse = clientResourceFeign.findByCpf(cpf);
            ResponseEntity<List<ClientCard>> clientCardList = cardResourceFeign.findCardsPerClient(cpf);

            return SituationCredit
                    .builder()
                    .client(clientResponse.getBody())
                    .listClientCard(clientCardList.getBody())
                    .build();
        } catch(FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException();
            }
            throw new ErrorComunicationMicroServicesException(e.getMessage(), status);
        }
    }

    public ReturnAvaliationCard avaliateCard(String cpf, Long amount) throws ClientDataNotFoundException,
            ErrorComunicationMicroServicesException {

        try {

            ResponseEntity<ClientData> clientResponse = clientResourceFeign.findByCpf(cpf);
            ResponseEntity<List<Card>> cardResponse = cardResourceFeign.getCardAmountTo(amount);

            List<Card> cards = cardResponse.getBody();
            assert cards != null;

            var listCardsApproved = cards.stream().map(card -> {

                ClientData clientData = clientResponse.getBody();

                BigDecimal basicLimit = card.getBasicLimit();
                BigDecimal ageBD = BigDecimal.valueOf(clientData.getAge());
                var res = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal limitApproved = res.multiply(basicLimit);

                CardApproved approved = new CardApproved();
                approved.setCard(card.getName());
                approved.setFlag(card.getCard());
                approved.setLimitApproved(limitApproved);

                return approved;
            }).collect(Collectors.toList());

            return new ReturnAvaliationCard(listCardsApproved);

        } catch(FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException();
            }
            throw new ErrorComunicationMicroServicesException(e.getMessage(), status);
        }

    }

}
