package io.github.cartoes.mscreditappraiser.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DataEmitCard {

    private Long idCard;
    private String cpf;
    private String address;
    private BigDecimal limitApproved;
}
