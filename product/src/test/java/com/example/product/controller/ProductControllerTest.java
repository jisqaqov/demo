package com.example.product.controller;


import static com.example.product.utils.TestDataHolder.TEST_PRODUCT_ID;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.product.model.ProductResponse;
import com.example.product.service.ProductService;
import com.example.product.utils.TestDataHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.sql.DataSource;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@Tag("unit")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ProductService productService;

  @Test
  void shouldFind() throws Exception {
    ProductResponse productResponse = TestDataHolder.buildProductResponse();
    when(productService.find(TEST_PRODUCT_ID))
      .thenReturn(productResponse);

    mockMvc.perform(
      get(ProductController.ROOT + "/{id}", TEST_PRODUCT_ID)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isOk())
      /*.andExpect(content().json(objectMapper.writeValueAsString(productResponse), true))*/;

    verify(productService).find(TEST_PRODUCT_ID);
    verifyNoMoreInteractions(productService);
  }

}