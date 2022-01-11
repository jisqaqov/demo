package com.example.review.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.review.model.dto.ErrorResponse;
import com.example.review.model.exception.EntityNotFoundException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
class ApiExceptionHandlerTest {

  private ApiExceptionHandler handler = new ApiExceptionHandler();

  @Test
  void test() {
    ErrorResponse response = handler
      .handleException(new EntityNotFoundException("TEST_MESSAGE"));

    assertEquals("TEST_MESSAGE", response.getMessage());
  }

}
