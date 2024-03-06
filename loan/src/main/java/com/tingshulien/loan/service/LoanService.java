package com.tingshulien.loan.service;

import com.tingshulien.loan.dto.LoanDto;

public interface LoanService {

  /**
   * Create a new loan
   *
   * @param mobileNumber the mobile number
   */
  void createLoan(String mobileNumber);

  /**
   * Fetch loan details
   *
   * @param mobileNumber the mobile number
   * @return the loan details
   */
  LoanDto fetchLoan(String mobileNumber);

  /**
   * Update loan details
   *
   * @param loanDto the loan data
   * @return the status of the update
   */
  boolean updateLoan(LoanDto loanDto);

  /**
   * Delete loan details
   *
   * @param mobileNumber the mobile number
   * @return the status of the delete
   */
  boolean deleteLoan(String mobileNumber);

}
