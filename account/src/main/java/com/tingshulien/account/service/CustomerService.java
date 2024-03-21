package com.tingshulien.account.service;

import com.tingshulien.account.dto.CustomerDetailDto;

public interface CustomerService {

  /**
   * Fetch customer detail by mobile number
   *
   * @param mobileNumber mobile number of the customer
   * @return customer detail
   */
  CustomerDetailDto fetchCustomerDetail(String mobileNumber);

}
