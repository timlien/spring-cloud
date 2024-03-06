package com.tingshulien.account.repository;

import com.tingshulien.account.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

  Optional<Account> findByCustomerId(Long customerId);

  @Modifying
  @Transactional
  void deleteByCustomerId(Long customerId);

}
