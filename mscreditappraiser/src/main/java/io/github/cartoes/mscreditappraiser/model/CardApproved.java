package io.github.cartoes.mscreditappraiser.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardApproved {

    private String card;
    private String flag;
    private BigDecimal limitApproved;
}
