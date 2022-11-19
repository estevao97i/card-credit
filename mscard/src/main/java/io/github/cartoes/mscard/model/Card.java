package io.github.cartoes.mscard.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FlagCard card;

    private BigDecimal amount;

    private BigDecimal basicLimit;

    public Card(String name, FlagCard card, BigDecimal amount, BigDecimal basicLimit) {
        this.name = name;
        this.card = card;
        this.amount = amount;
        this.basicLimit = basicLimit;
    }
}
