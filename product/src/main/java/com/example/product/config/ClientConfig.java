package com.example.product.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {

  @Bean
  @Primary
  public RestTemplate restTemplate() {
    return new RestTemplateBuilder().build();
  }

}
