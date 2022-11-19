package io.github.cartoes.mscreditappraiser.service;

import feign.FeignException;
import io.github.cartoes.mscreditappraiser.exception.ClientDataNotFoundException;
import io.github.cartoes.mscreditappraiser.exception.ErrorComunicationMicroServicesException;
import io.github.cartoes.mscreditappraiser.feignClients.CardResourceFeign;
import io.github.cartoes.mscreditappraiser.feignClients.ClientResourceFeign;
import io.github.cartoes.mscreditappraiser.model.ClientCard;
import io.github.cartoes.mscreditappraiser.model.ClientData;
import io.github.cartoes.mscreditappraiser.model.SituationCredit;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
