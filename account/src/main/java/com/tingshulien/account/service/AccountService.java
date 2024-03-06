package com.tingshulien.account.service;

import com.tingshulien.account.dto.CustomerDto;

public interface AccountService {

  /**
   * Create a new account
   *
   * @param customerDto the customer data
   */
  void createAccount(CustomerDto customerDto);

  /**
   * Fetch account details
   *
   * @param mobileNumber the mobile number
   * @return the account details
   */
  CustomerDto fetchAccount(String mobileNumber);

  /**
   * Update account details
   *
   * @param customerDto the customer data
   * @return the status of the update
   */
  boolean updateAccount(CustomerDto customerDto);

  /**
   * Delete account details
   *
   * @param mobileNumber the mobile number
   * @return the status of the delete
   */
  boolean deleteAccount(String mobileNumber);

}
