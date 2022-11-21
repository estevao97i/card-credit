package io.github.cartoes.mscreditappraiser.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Card {

    private Long id;
    private String name;
    private String card;
    private BigDecimal amount;
    private BigDecimal basicLimit;
}
