package com.example.product.utils;

import com.example.product.model.ProductDto;
import com.example.product.model.ProductResponse;
import com.example.product.model.ReviewDto;

public class TestDataHolder {
  public static final String TEST_PRODUCT_ID = "123";

  public static ProductResponse buildProductResponse() {
    return ProductResponse.builder()
      .id(TEST_PRODUCT_ID)
      .averageReviewScore(23)
      .reviewsNumber(52)
      .productType("TEST")
      .modelNumber("MODEL")
      .name("NAME")
      .build();
  }

  public static ProductDto buildProductDto() {
    return ProductDto.builder()
      .id(TEST_PRODUCT_ID)
      .modelNumber("123")
      .name("NAME")
      .productType("TEST")
      .build();
  }

  public static ReviewDto buildReviewDto() {
    return ReviewDto.builder()
      .id(TEST_PRODUCT_ID)
      .averageReviewScore(33)
      .reviewsNumber(31)
      .build();
  }

}
