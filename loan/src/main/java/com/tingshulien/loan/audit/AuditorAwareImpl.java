package com.tingshulien.loan.audit;

import jakarta.annotation.Nonnull;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

  @Nonnull
  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.of("LOAN_SERVICE_USER");
  }

}
