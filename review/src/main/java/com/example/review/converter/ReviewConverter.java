package com.example.review.converter;

import com.example.review.model.dto.ReviewDto;
import com.example.review.model.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewConverter {

    private final ModelMapper mapper;

    public ReviewDto convertReviewEntityToDto(ReviewEntity review) {
        return mapper.map(review, ReviewDto.class);
    }

    public ReviewEntity convertDtoToReviewEntity(ReviewDto reviewDto) {
        return mapper.map(reviewDto, ReviewEntity.class);
    }

}
