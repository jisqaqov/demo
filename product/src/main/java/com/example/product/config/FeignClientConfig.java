package com.example.product.config;

import com.example.product.client.ReviewFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@EnableFeignClients(clients = ReviewFeignClient.class)
@PropertySource("classpath:feign-client.properties")
public class FeignClientConfig {

	@Bean
	@ConditionalOnProperty(name = {
			"security.client.basic.auth.username",
			"security.client.basic.auth.password"})
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
	    @Value("${security.client.basic.auth.username}") final String username,
	    @Value("${security.client.basic.auth.password}") final String password) {
	  return new BasicAuthRequestInterceptor(username, password);
	}

}
