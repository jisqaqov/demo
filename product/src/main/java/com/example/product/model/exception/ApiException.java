package com.example.product.model.exception;

public class ApiException extends RuntimeException {

  public ApiException(String message) {
    super(message);
  }
}
