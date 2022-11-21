package io.github.cartoes.mscreditappraiser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class DataAvaliation {

    private String cpf;
    private Long amount;
}
