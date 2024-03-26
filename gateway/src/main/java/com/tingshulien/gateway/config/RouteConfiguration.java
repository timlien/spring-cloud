package com.tingshulien.gateway.config;

import java.time.LocalDateTime;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
    return routeLocatorBuilder.routes()
        .route(p -> p
            .path("/account/**")
            .filters(f -> f.rewritePath("/account/(?<segment>.*)", "/${segment}")
                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
            .uri("lb://ACCOUNT-SERVICE")
        )
        .route(p -> p
            .path("/loan/**")
            .filters(f -> f.rewritePath("/loan/(?<segment>.*)", "/${segment}")
                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
            .uri("lb://LOAN-SERVICE")
        )
        .route(p -> p
            .path("/card/**")
            .filters(f -> f.rewritePath("/card/(?<segment>.*)", "/${segment}")
                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
            .uri("lb://CARD-SERVICE")
        )
        .build();
  }

}
