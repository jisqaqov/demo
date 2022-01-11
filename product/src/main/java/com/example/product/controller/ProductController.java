package com.example.product.controller;

import static com.example.product.controller.ProductController.ROOT;

import com.example.product.model.ProductResponse;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ROOT)
public class ProductController {
  public static final String ROOT = "/product";

  @Autowired
  private ProductService productService;

  @GetMapping(value = "/{id}")
  public ProductResponse find(@PathVariable String id) {
    return productService.find(id);
  }

}
