package com.tingshulien.account.client;

import com.tingshulien.account.dto.LoanDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoanFallback implements LoanFeignClient {

  @Override
  public ResponseEntity<LoanDto> fetchLoanDetails(String mobileNumber) {
    return null;
  }

}
