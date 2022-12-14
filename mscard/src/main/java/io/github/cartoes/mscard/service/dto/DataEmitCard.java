package io.github.cartoes.mscard.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DataEmitCard {

    private Long idCard;
    private String cpf;
    private String address;
    private BigDecimal limitApproved;
}