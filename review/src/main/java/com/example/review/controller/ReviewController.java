package com.example.review.controller;

import com.example.review.model.dto.ReviewDto;
import com.example.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ReviewController.ROOT)
public class ReviewController {
  public static final String ROOT = "/reviews";

  @Autowired
  private ReviewService reviewService;

  @GetMapping("/{id}")
  public ReviewDto find(@PathVariable String id) {
    return reviewService.find(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ReviewDto create(@RequestBody ReviewDto reviewDto) {
    return reviewService.create(reviewDto);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ReviewDto update(@PathVariable("id") String id, @RequestBody ReviewDto reviewDto) {
    return reviewService.update(id, reviewDto);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id) {
    reviewService.delete(id);
  }

}
