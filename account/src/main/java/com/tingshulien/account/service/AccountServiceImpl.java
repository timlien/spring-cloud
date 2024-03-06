package com.tingshulien.account.service;

import com.tingshulien.account.constants.AccountConstants;
import com.tingshulien.account.dto.AccountDto;
import com.tingshulien.account.dto.CustomerDto;
import com.tingshulien.account.entity.Account;
import com.tingshulien.account.entity.Customer;
import com.tingshulien.account.exception.CustomerAlreadyExistsException;
import com.tingshulien.account.exception.ResourceNotFoundException;
import com.tingshulien.account.mapper.AccountMapper;
import com.tingshulien.account.mapper.CustomerMapper;
import com.tingshulien.account.repository.AccountRepository;
import com.tingshulien.account.repository.CustomerRepository;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  private final CustomerRepository customerRepository;

  private final CustomerMapper customerMapper;

  private final AccountMapper accountMapper;

  @Override
  public void createAccount(CustomerDto customerDto) {
    if (customerRepository.existsByMobileNumber(customerDto.getMobileNumber())) {
      throw new CustomerAlreadyExistsException(
          "Customer with mobile number " + customerDto.getMobileNumber() + " already exists");
    }

    Customer customer = customerMapper.toEntity(customerDto);
    Customer newCustomer = customerRepository.save(customer);
    Account account = createNewAccount(newCustomer);
    accountRepository.save(account);
  }

  private Account createNewAccount(Customer customer) {
    Account newAccount = new Account();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(AccountConstants.SAVINGS);
    newAccount.setBranchAddress(AccountConstants.ADDRESS);
    return newAccount;
  }

  @Override
  public CustomerDto fetchAccount(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

    Account account = accountRepository.findByCustomerId(customer.getCustomerId())
        .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId",
            customer.getCustomerId().toString()));

    CustomerDto customerDto = customerMapper.toDto(customer);
    AccountDto accountDto = accountMapper.toDto(account);
    customerDto.setAccountDto(accountDto);

    return customerDto;
  }

  @Override
  public boolean updateAccount(CustomerDto customerDto) {
    AccountDto accountsDto = customerDto.getAccountDto();
    if (accountsDto == null) {
      return false;
    }

    Account account = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
        () -> new ResourceNotFoundException("Account", "AccountNumber",
            accountsDto.getAccountNumber().toString())
    );

    account.setAccountType(accountsDto.getAccountType());
    account.setBranchAddress(accountsDto.getBranchAddress());
    account = accountRepository.save(account);

    Long customerId = account.getCustomerId();
    Customer customer = customerRepository.findById(customerId).orElseThrow(
        () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
    );

    customer.setName(customerDto.getName());
    customer.setEmail(customerDto.getEmail());
    customer.setMobileNumber(customerDto.getMobileNumber());
    customerRepository.save(customer);
    return true;
  }

  @Override
  public boolean deleteAccount(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

    accountRepository.deleteByCustomerId(customer.getCustomerId());
    customerRepository.delete(customer);
    return true;
  }

}
