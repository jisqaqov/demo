package com.example.product.client;

import static com.example.product.utils.TestDataHolder.TEST_PRODUCT_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.product.model.ProductDto;
import com.example.product.utils.TestDataHolder;
import java.util.Optional;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Tag("unit")
@SpringBootTest
public class AdidasApiClientTest {

  @Autowired
  private AdidasApiClient adidasApiClient;
  @MockBean
  private RestTemplate restTemplate;

  @Test
  public void searchProduct() {
    ProductDto productDto = TestDataHolder.buildProductDto();

    when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), any(ParameterizedTypeReference.class)))
      .thenReturn(ResponseEntity.of(Optional.of(productDto)));

    ProductDto response = adidasApiClient.searchProduct(TEST_PRODUCT_ID);

    assertEquals(productDto, response);
  }

}
