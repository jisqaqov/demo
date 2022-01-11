package com.example.product.service;

import static com.example.product.utils.TestDataHolder.TEST_PRODUCT_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.example.product.client.AdidasApiClient;
import com.example.product.client.ReviewFeignClient;
import com.example.product.model.ProductDto;
import com.example.product.model.ProductResponse;
import com.example.product.model.ReviewDto;
import com.example.product.utils.TestDataHolder;
import javax.sql.DataSource;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@Tag("unit")
@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

  @Autowired
  private ProductService productService;
  @MockBean
  private AdidasApiClient adidasApiClient;
  @MockBean
  private ReviewFeignClient reviewFeignClient;

  @Test
  public void find() {
    ProductResponse productResponse = TestDataHolder.buildProductResponse();
    ProductDto productDto = TestDataHolder.buildProductDto();
    ReviewDto reviewDto = TestDataHolder.buildReviewDto();

    when(adidasApiClient.searchProduct(TEST_PRODUCT_ID))
      .thenReturn(productDto);
    when(reviewFeignClient.find(TEST_PRODUCT_ID))
      .thenReturn(reviewDto);

    ProductResponse response = productService.find(TEST_PRODUCT_ID);

    verify(adidasApiClient).searchProduct(TEST_PRODUCT_ID);
    verifyNoMoreInteractions(adidasApiClient);

    verify(reviewFeignClient).find(TEST_PRODUCT_ID);
    verifyNoMoreInteractions(reviewFeignClient);

    assertEquals(reviewDto.getId(), response.getId());
    assertEquals(productDto.getModelNumber(), response.getModelNumber());
    assertEquals(productDto.getName(), response.getName());
    assertEquals(productDto.getProductType(), response.getProductType());
    assertEquals(reviewDto.getAverageReviewScore(), response.getAverageReviewScore());
    assertEquals(reviewDto.getReviewsNumber(), response.getReviewsNumber());
  }

}

