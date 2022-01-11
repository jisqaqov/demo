package com.example.product.service;

import com.example.product.client.AdidasApiClient;
import com.example.product.client.ReviewFeignClient;
import com.example.product.model.ProductDto;
import com.example.product.model.ProductResponse;
import com.example.product.model.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  private ReviewFeignClient reviewFeignClient;
  @Autowired
  private AdidasApiClient adidasApiClient;

  public ProductResponse find(String id) {
    ReviewDto reviewDto = reviewFeignClient.find(id);

    ProductDto productDto = adidasApiClient.searchProduct(id);

    return toProductResponse(reviewDto, productDto);
  }

  private ProductResponse toProductResponse(ReviewDto reviewDto, ProductDto productDto) {
    return ProductResponse.builder()
      .id(reviewDto.getId())
      .name(productDto.getName())
      .modelNumber(productDto.getModelNumber())
      .productType(productDto.getProductType())
      .averageReviewScore(reviewDto.getAverageReviewScore())
      .reviewsNumber(reviewDto.getReviewsNumber())
      .build();
  }

}
