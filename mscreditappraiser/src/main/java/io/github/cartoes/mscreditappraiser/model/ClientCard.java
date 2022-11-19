package io.github.cartoes.mscreditappraiser.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ClientCard {

    private String name;
    private String flag;
    private BigDecimal basicLimit;

}
