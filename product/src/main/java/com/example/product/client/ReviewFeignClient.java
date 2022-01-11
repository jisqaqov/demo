package com.example.product.client;

import com.example.product.config.FeignClientConfig;
import com.example.product.model.ReviewDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "reviewClient", path = "reviews",
    url = "${review.client.url:localhost}",
    configuration = FeignClientConfig.class)
public interface ReviewFeignClient {
	
    @GetMapping(value = "/{id}", produces = "application/json")
    ReviewDto find(@PathVariable("id") String id);

}