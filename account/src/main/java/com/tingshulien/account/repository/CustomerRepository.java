package com.tingshulien.account.repository;

import com.tingshulien.account.entity.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  boolean existsByMobileNumber(String mobileNumber);

  Optional<Customer> findByMobileNumber(String mobileNumber);

}
