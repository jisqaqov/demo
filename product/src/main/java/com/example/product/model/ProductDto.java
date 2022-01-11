package com.example.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
  private String id;
  @JsonProperty("product_type")
  private String productType;
  @JsonProperty("model_number")
  private String modelNumber;
  @JsonProperty("name")
  private String name;
}
