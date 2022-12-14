package io.github.cartoes.mscard.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cartoes.mscard.model.Card;
import io.github.cartoes.mscard.model.ClientCard;
import io.github.cartoes.mscard.repository.CardRepository;
import io.github.cartoes.mscard.repository.ClientCardRepository;
import io.github.cartoes.mscard.service.dto.DataEmitCard;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmitCardSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receptEmit(@Payload String payload) {

        try {
            var mapper = new ObjectMapper();
            DataEmitCard data = mapper.readValue(payload, DataEmitCard.class);
            Card card = cardRepository.findById(data.getIdCard()).orElseThrow();

            ClientCard clientCard = new ClientCard();
            clientCard.setCard(card);
            clientCard.setBasicLimit(card.getBasicLimit());

            clientCardRepository.save(clientCard);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
