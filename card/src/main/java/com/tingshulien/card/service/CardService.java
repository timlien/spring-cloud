package com.tingshulien.card.service;

import com.tingshulien.card.dto.CardDto;

public interface CardService {

  /**
   * Create a new card
   *
   * @param mobileNumber the mobile number
   */
  void createCard(String mobileNumber);

  /**
   * Fetch card details
   *
   * @param mobileNumber the mobile number
   * @return the card details
   */
  CardDto fetchCard(String mobileNumber);

  /**
   * Update card details
   *
   * @param cardDto the card data
   * @return the status of the update
   */
  boolean updateCard(CardDto cardDto);

  /**
   * Delete card details
   *
   * @param mobileNumber the mobile number
   * @return the status of the delete
   */
  boolean deleteCard(String mobileNumber);

}
