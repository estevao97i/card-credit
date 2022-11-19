package io.github.cartoes.mscard.service.dto;

import io.github.cartoes.mscard.model.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientCardDto {

    private String name;
    private String flag;
    private BigDecimal limitLine;

    public static ClientCardDto fromModel(ClientCard model) {
        return new ClientCardDto(
                model.getCard().getName(),
                model.getCard().getCard().toString(),
                model.getBasicLimit());
    }
}
