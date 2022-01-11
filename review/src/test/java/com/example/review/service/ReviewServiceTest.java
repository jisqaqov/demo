package com.example.review.service;

import static com.example.review.util.TestDataHolder.TEST_PRODUCT_ID;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.example.review.converter.ReviewConverter;
import com.example.review.model.dto.ReviewDto;
import com.example.review.model.entity.ReviewEntity;
import com.example.review.model.exception.EntityNotFoundException;
import com.example.review.repostiory.ReviewRepository;
import com.example.review.util.TestDataHolder;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ReviewServiceTest {

  @Autowired
  private ReviewService reviewService;
  @MockBean
  private ReviewRepository reviewRepository;
  @MockBean
  private ReviewConverter reviewConverter;

  @Test
  public void find() {
    ReviewDto reviewDto = TestDataHolder.buildReviewDto();
    ReviewEntity reviewEntity = TestDataHolder.buildReviewEntity();

    when(reviewRepository.findById(TEST_PRODUCT_ID))
      .thenReturn(Optional.of(reviewEntity));
    when(reviewConverter.convertReviewEntityToDto(reviewEntity))
      .thenReturn(reviewDto);

    ReviewDto response = assertDoesNotThrow(() -> reviewService.find(TEST_PRODUCT_ID));

    assertEquals(reviewDto, response);

    verify(reviewRepository).findById(TEST_PRODUCT_ID);
    verifyNoMoreInteractions(reviewRepository);

    verify(reviewConverter).convertReviewEntityToDto(reviewEntity);
    verifyNoMoreInteractions(reviewConverter);
  }

  @Test
  public void failFindWhenNoProductFound() {
    when(reviewRepository.findById(TEST_PRODUCT_ID))
      .thenReturn(Optional.empty());

    assertThrows((EntityNotFoundException.class), () -> reviewService.find(TEST_PRODUCT_ID));

    verify(reviewRepository).findById(TEST_PRODUCT_ID);
    verifyNoMoreInteractions(reviewRepository);

    verifyNoInteractions(reviewConverter);
  }

  @Test
  public void create() {
    ReviewDto requestReview = TestDataHolder.buildReviewDto();
    ReviewDto responseReview = TestDataHolder.buildReviewDto();

    ReviewEntity entityToSave = TestDataHolder.buildReviewEntity();
    ReviewEntity savedEntity = TestDataHolder.buildReviewEntity();

    when(reviewRepository.save(entityToSave))
      .thenReturn(savedEntity);
    when(reviewConverter.convertDtoToReviewEntity(requestReview))
      .thenReturn(entityToSave);
    when(reviewConverter.convertReviewEntityToDto(savedEntity))
      .thenReturn(responseReview);

    ReviewDto response = assertDoesNotThrow(() -> reviewService.create(requestReview));

    assertEquals(responseReview, response);

    verify(reviewRepository).save(entityToSave);
    verifyNoMoreInteractions(reviewRepository);

    verify(reviewConverter).convertReviewEntityToDto(savedEntity);
    verify(reviewConverter).convertDtoToReviewEntity(requestReview);
    verifyNoMoreInteractions(reviewConverter);
  }

  @Test
  public void update() {
    ReviewDto requestReview = TestDataHolder.buildReviewDto();
    ReviewDto responseReview = TestDataHolder.buildReviewDto();

    ReviewEntity entityToUpdate = TestDataHolder.buildReviewEntity();
    ReviewEntity savedEntity = TestDataHolder.buildReviewEntity();

    when(reviewRepository.findById(TEST_PRODUCT_ID))
      .thenReturn(Optional.of(TestDataHolder.buildReviewEntity()));

    when(reviewRepository.save(entityToUpdate))
      .thenReturn(savedEntity);
    when(reviewConverter.convertDtoToReviewEntity(requestReview))
      .thenReturn(entityToUpdate);
    when(reviewConverter.convertReviewEntityToDto(savedEntity))
      .thenReturn(responseReview);

    ReviewDto response = assertDoesNotThrow(
      () -> reviewService.update(requestReview.getId(), requestReview));

    assertEquals(responseReview, response);

    verify(reviewRepository).findById(TEST_PRODUCT_ID);
    verify(reviewRepository).save(entityToUpdate);
    verifyNoMoreInteractions(reviewRepository);

    verify(reviewConverter).convertReviewEntityToDto(savedEntity);
    verify(reviewConverter).convertDtoToReviewEntity(requestReview);
    verifyNoMoreInteractions(reviewConverter);
  }

  @Test
  public void delete() {
    when(reviewRepository.findById(TEST_PRODUCT_ID))
      .thenReturn(Optional.of(TestDataHolder.buildReviewEntity()));

    doNothing().when(reviewRepository).deleteById(TEST_PRODUCT_ID);

    assertDoesNotThrow(() -> reviewService.delete(TEST_PRODUCT_ID));

    verify(reviewRepository).findById(TEST_PRODUCT_ID);
    verify(reviewRepository).deleteById(TEST_PRODUCT_ID);
    verifyNoMoreInteractions(reviewRepository);

    verifyNoInteractions(reviewConverter);
  }

  @Test
  public void failDeleteWhenNoProductFound() {
    when(reviewRepository.findById(TEST_PRODUCT_ID))
      .thenReturn(Optional.empty());

    assertThrows((EntityNotFoundException.class), () -> reviewService.delete(TEST_PRODUCT_ID));

    verify(reviewRepository).findById(TEST_PRODUCT_ID);
    verifyNoMoreInteractions(reviewRepository);

    verifyNoInteractions(reviewConverter);
  }

}
