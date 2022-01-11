package com.example.review.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "reviews")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {
  @Id
  @Column(name = "id")
  private String id;
  @Column(name = "average_review_score")
  private Integer averageReviewScore;
  @Column(name = "reviews_number")
  private Integer reviewsNumber;

}
