package com.tingshulien.account.client;

import com.tingshulien.account.dto.CardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardFallback implements CardFeignClient {

  @Override
  public ResponseEntity<CardDto> fetchCard(String mobileNumber) {
    return null;
  }

}
