package io.github.cartoes.mscard.service;

import io.github.cartoes.mscard.model.Card;
import io.github.cartoes.mscard.repository.CardRepository;
import io.github.cartoes.mscard.service.dto.CardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CardService {

    private final CardRepository repository;

    public Card save(Card card) {
        return repository.save(card);
    }

    public List<Card> getCardsAmountLessEqual(Long amount) {
        var card = BigDecimal.valueOf(amount);
        return repository.findByAmountLessThanEqual(card);
    }

    public List<Card> findAll() {
        return repository.findAll();
    }
}
