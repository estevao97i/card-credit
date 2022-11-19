package io.github.cartoes.mscard.repository;

import io.github.cartoes.mscard.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByAmountLessThanEqual(BigDecimal amount);

}
