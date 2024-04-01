package com.tingshulien.gateway.config;

import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

@Configuration
public class RouteConfiguration {

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
    return routeLocatorBuilder.routes()
        .route(p -> p
            .path("/account/**")
            .filters(f -> f.rewritePath("/account/(?<segment>.*)", "/${segment}")
                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                .circuitBreaker(config -> config.setName("account-circuit-breaker")
                    .setFallbackUri("forward:/fallback/account"))

            )
            .uri("lb://ACCOUNT-SERVICE")
        )
        .route(p -> p
            .path("/loan/**")
            .filters(f -> f.rewritePath("/loan/(?<segment>.*)", "/${segment}")
                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                .retry(config -> config.setRetries(3)
                    .setMethods(HttpMethod.GET)
                    .setBackoff(Duration.ofMillis(200), Duration.ofMillis(1000), 2, true))
            )
            .uri("lb://LOAN-SERVICE")
        )
        .route(p -> p
            .path("/card/**")
            .filters(f -> f.rewritePath("/card/(?<segment>.*)", "/${segment}")
                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                    .setKeyResolver(keyResolver()))
            )
            .uri("lb://CARD-SERVICE")
        )
        .build();
  }

  @Bean
  public RedisRateLimiter redisRateLimiter() {
    return new RedisRateLimiter(1, 1, 1);
  }

  @Bean
  public KeyResolver keyResolver() {
    return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
        .defaultIfEmpty("anonymous");
  }

}
