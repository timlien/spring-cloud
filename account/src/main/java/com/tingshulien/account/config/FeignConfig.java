package com.tingshulien.account.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.tingshulien.account"})
public class FeignConfig {

}
