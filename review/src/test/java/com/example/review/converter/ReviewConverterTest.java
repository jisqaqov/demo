package com.example.review.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.review.model.dto.ReviewDto;
import com.example.review.model.entity.ReviewEntity;
import com.example.review.util.TestDataHolder;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Tag("unit")
@SpringBootTest
public class ReviewConverterTest {
  @Autowired
  private ReviewConverter converter;

  @Test
  public void convertDtoToEntity() {
    ReviewDto reviewDto = TestDataHolder.buildReviewDto();

    ReviewEntity entity = converter.convertDtoToReviewEntity(reviewDto);

    assertEquals(reviewDto.getId(), entity.getId());
    assertEquals(reviewDto.getAverageReviewScore(), entity.getAverageReviewScore());
    assertEquals(reviewDto.getReviewsNumber(), entity.getReviewsNumber());
  }

}

