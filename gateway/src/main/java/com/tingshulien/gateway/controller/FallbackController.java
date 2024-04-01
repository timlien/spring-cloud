package com.tingshulien.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

  @GetMapping("/fallback/account")
  public String accountFallback() {
    return "Account service is down. Please try again later.";
  }

  @GetMapping("/fallback/loan")
  public String loanFallback() {
    return "Loan service is down. Please try again later.";
  }

  @GetMapping("/fallback/card")
  public String cardFallback() {
    return "Card service is down. Please try again later.";
  }

}
