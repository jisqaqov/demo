package com.example.product.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

  private String id;
  private Integer averageReviewScore;
  private Integer reviewsNumber;

  private String productType;
  private String modelNumber;
  private String name;

}
