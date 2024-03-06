package com.tingshulien.card.config;

import com.tingshulien.card.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfig {

  @Bean
  public AuditorAwareImpl auditorAware() {
    return new AuditorAwareImpl();
  }

}
