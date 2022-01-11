package com.example.product.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
  private String id;
  private Integer averageReviewScore;
  private Integer reviewsNumber;
}
