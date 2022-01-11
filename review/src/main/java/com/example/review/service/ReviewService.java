package com.example.review.service;

import com.example.review.converter.ReviewConverter;
import com.example.review.model.dto.ReviewDto;
import com.example.review.model.entity.ReviewEntity;
import com.example.review.model.exception.EntityNotFoundException;
import com.example.review.repostiory.ReviewRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

  @Autowired
  private ReviewRepository reviewRepository;
  @Autowired
  private ReviewConverter reviewConverter;

  public ReviewDto update(String id, ReviewDto reviewDto) {
    reviewDto.setId(id);

    Optional<ReviewEntity> optional = reviewRepository.findById(id);
    if (optional.isEmpty()) {
      throw new EntityNotFoundException(
        String.format("Review with id = %s not found error ", id));
    }

    ReviewEntity entityToUpdate = reviewConverter.convertDtoToReviewEntity(reviewDto);
    ReviewEntity savedEntity = reviewRepository.save(entityToUpdate);

    return reviewConverter.convertReviewEntityToDto(savedEntity);
  }

  public ReviewDto create(ReviewDto reviewDto) {
    ReviewEntity entityToCreate = reviewConverter.convertDtoToReviewEntity(reviewDto);
    ReviewEntity savedEntity = reviewRepository.save(entityToCreate);

    return reviewConverter.convertReviewEntityToDto(savedEntity);
  }

  public ReviewDto find(String id) {
    return reviewRepository.findById(id)
      .map(entity -> reviewConverter.convertReviewEntityToDto(entity))
      .orElseThrow(() -> new EntityNotFoundException(
        String.format("Review with id = %s not found error ", id)));
  }

  public void delete(String id) {
    Optional<ReviewEntity> reviewEntity = reviewRepository.findById(id);
    if (reviewEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("Review with id = %s not found error ", id));
    }

    reviewRepository.deleteById(id);
  }

}
