package com.tingshulien.card.service;

import com.tingshulien.card.constant.CardConstants;
import com.tingshulien.card.dto.CardDto;
import com.tingshulien.card.entity.Card;
import com.tingshulien.card.exception.CardAlreadyExistsException;
import com.tingshulien.card.exception.ResourceNotFoundException;
import com.tingshulien.card.mapper.CardMapper;
import com.tingshulien.card.repository.CardRepository;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

  private final CardRepository cardRepository;

  private final CardMapper cardMapper;

  @Override
  public void createCard(String mobileNumber) {
    Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);
    if (optionalCard.isPresent()) {
      throw new CardAlreadyExistsException(
          "Card already registered with given mobileNumber " + mobileNumber);
    }

    cardRepository.save(createNewCard(mobileNumber));
  }

  private Card createNewCard(String mobileNumber) {
    Card newCard = new Card();
    long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
    newCard.setCardNumber(Long.toString(randomCardNumber));
    newCard.setMobileNumber(mobileNumber);
    newCard.setCardType(CardConstants.CREDIT_CARD);
    newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
    newCard.setAmountUsed(0);
    newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
    return newCard;
  }

  @Override
  public CardDto fetchCard(String mobileNumber) {
    Card card = cardRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));

    return cardMapper.toDto(card);
  }

  @Override
  public boolean updateCard(CardDto cardDto) {
    Card card = cardRepository.findByCardNumber(cardDto.getCardNumber())
        .orElseThrow(
            () -> new ResourceNotFoundException("Card", "mobileNumber", cardDto.getMobileNumber()));

    card.setCardNumber(cardDto.getCardNumber());
    card.setCardType(cardDto.getCardType());
    card.setMobileNumber(cardDto.getMobileNumber());
    card.setTotalLimit(cardDto.getTotalLimit());
    card.setAvailableAmount(cardDto.getAvailableAmount());
    card.setAmountUsed(cardDto.getAmountUsed());
    cardRepository.save(card);
    return true;
  }

  @Override
  public boolean deleteCard(String mobileNumber) {
    Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);
    if (optionalCard.isEmpty()) {
      throw new ResourceNotFoundException("Card", "mobileNumber", mobileNumber);
    }

    cardRepository.delete(optionalCard.get());
    return true;
  }

}
