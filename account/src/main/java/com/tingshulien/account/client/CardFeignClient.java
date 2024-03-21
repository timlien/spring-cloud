package com.tingshulien.account.client;

import com.tingshulien.account.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "card-service")
public interface CardFeignClient {

  @GetMapping(value = "/api/v1/fetch", consumes = "application/json")
  ResponseEntity<CardDto> fetchCard(@RequestParam String mobileNumber);

}
