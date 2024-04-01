package com.tingshulien.account.service;

import com.tingshulien.account.client.CardFeignClient;
import com.tingshulien.account.client.LoanFeignClient;
import com.tingshulien.account.dto.AccountDto;
import com.tingshulien.account.dto.CardDto;
import com.tingshulien.account.dto.CustomerDetailDto;
import com.tingshulien.account.dto.LoanDto;
import com.tingshulien.account.entity.Account;
import com.tingshulien.account.entity.Customer;
import com.tingshulien.account.exception.ResourceNotFoundException;
import com.tingshulien.account.mapper.AccountMapper;
import com.tingshulien.account.mapper.CustomerDetailMapper;
import com.tingshulien.account.repository.AccountRepository;
import com.tingshulien.account.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final AccountRepository accountRepository;

  private final CustomerRepository customerRepository;

  private final AccountMapper accountMapper;

  private final CustomerDetailMapper customerDetailMapper;

  private final CardFeignClient cardFeignClient;

  private final LoanFeignClient loanFeignClient;

  @Override
  public CustomerDetailDto fetchCustomerDetail(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
    CustomerDetailDto customerDetailDto = customerDetailMapper.toDto(customer);

    Account account = accountRepository.findByCustomerId(customer.getCustomerId())
        .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId",
            customer.getCustomerId().toString()));
    AccountDto accountDto = accountMapper.toDto(account);

    ResponseEntity<CardDto> cardDtoResponseEntity = cardFeignClient.fetchCard(mobileNumber);
    ResponseEntity<LoanDto> loanDtoResponseEntity = loanFeignClient.fetchLoanDetails(mobileNumber);

    customerDetailDto.setAccountDto(accountDto);

    if (cardDtoResponseEntity != null) {
      customerDetailDto.setCardDto(cardDtoResponseEntity.getBody());
    }

    if (loanDtoResponseEntity != null) {
      customerDetailDto.setLoanDto(loanDtoResponseEntity.getBody());
    }

    return customerDetailDto;
  }

}
