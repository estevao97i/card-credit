package io.github.cartoes.mscard.service.dto;

import io.github.cartoes.mscard.model.Card;
import io.github.cartoes.mscard.model.FlagCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CardDto {

    private String name;
    private FlagCard card;
    private BigDecimal amount;
    private BigDecimal basicLimit;

    public Card toModel() {
        return new Card(name, card, amount, basicLimit);
    }
}
