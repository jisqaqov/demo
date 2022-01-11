package com.example.review.util;

import com.example.review.model.dto.ReviewDto;
import com.example.review.model.entity.ReviewEntity;

public class TestDataHolder {
  public static final String TEST_PRODUCT_ID = "123";

  public static ReviewDto buildReviewDto() {
    return ReviewDto.builder()
      .id(TEST_PRODUCT_ID)
      .averageReviewScore(23)
      .reviewsNumber(52)
      .build();
  }

  public static ReviewEntity buildReviewEntity() {
    return ReviewEntity.builder()
      .id(TEST_PRODUCT_ID)
      .averageReviewScore(23)
      .reviewsNumber(52)
      .build();
  }

}
