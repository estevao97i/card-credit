package io.github.cartoes.mscreditappraiser.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SituationCredit {

    private ClientData client;
    private List<ClientCard> listClientCard;
}
