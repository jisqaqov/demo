package com.example.product.client;

import com.example.product.model.ProductDto;
import com.example.product.model.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AdidasApiClient {
  @Autowired
  private RestTemplate restTemplate;
  @Value("${adidas.api.url}")
  private String adidasUrl;

  public ProductDto searchProduct(String id) {
    try {
      ResponseEntity<ProductDto> responseEntity = restTemplate
        .exchange(adidasUrl + "/" + id, HttpMethod.GET, null,
          new ParameterizedTypeReference<>() {
          });

      return responseEntity.getBody();
    } catch (RuntimeException e) {
      throw new ApiException(String.format("Could not retrieve product from endpoint %s", adidasUrl));
    }
  }


}
