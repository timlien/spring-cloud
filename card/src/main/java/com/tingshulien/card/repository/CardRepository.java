package com.tingshulien.card.repository;

import com.tingshulien.card.entity.Card;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

  Optional<Card> findByMobileNumber(String mobileNumber);

  Optional<Card> findByCardNumber(String cardNumber);

}
