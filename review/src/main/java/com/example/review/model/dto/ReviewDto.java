package com.example.review.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
  private String id;
  private Integer averageReviewScore;
  private Integer reviewsNumber;
}
