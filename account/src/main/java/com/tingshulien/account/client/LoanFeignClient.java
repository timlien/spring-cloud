package com.tingshulien.account.client;

import com.tingshulien.account.dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loan-service", fallback = LoanFallback.class)
public interface LoanFeignClient {

  @GetMapping(value = "/api/v1/fetch", consumes = "application/json")
  ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam String mobileNumber);

}
