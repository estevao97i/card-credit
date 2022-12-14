package io.github.cartoes.mscreditappraiser.ifra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cartoes.mscreditappraiser.model.DataEmitCard;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmitCardPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmitCard;

    public void sendCard(DataEmitCard card) throws JsonProcessingException {
        var json = convertToJson(card);
        rabbitTemplate.convertAndSend(queueEmitCard.getName(), json);
    }

    private String convertToJson(DataEmitCard data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(data);

        return json;
    }
}
