package com.example.review.controller;

import static com.example.review.util.TestDataHolder.TEST_PRODUCT_ID;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.review.model.dto.ReviewDto;
import com.example.review.service.ReviewService;
import com.example.review.util.TestDataHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@Tag("unit")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReviewService reviewService;

    @Test
    void shouldFind() throws Exception {
        ReviewDto reviewDto = TestDataHolder.buildReviewDto();
        when(reviewService.find(TEST_PRODUCT_ID))
            .thenReturn(reviewDto);

        mockMvc.perform(
          get(ReviewController.ROOT + "/{id}", TEST_PRODUCT_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(reviewDto), true));

        verify(reviewService).find(TEST_PRODUCT_ID);
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    void shouldCreate() throws Exception {
        ReviewDto request = TestDataHolder.buildReviewDto();

        mockMvc.perform(
            post(ReviewController.ROOT)
                .content(mapPayload(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated());

        verify(reviewService).create(request);
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    void shouldUpdate() throws Exception {
        ReviewDto request = TestDataHolder.buildReviewDto();

        mockMvc.perform(
          put(ReviewController.ROOT + "/{id}", TEST_PRODUCT_ID)
            .content(mapPayload(request))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk());

        verify(reviewService).update(TEST_PRODUCT_ID, request);
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(
          delete(ReviewController.ROOT + "/{id}", TEST_PRODUCT_ID)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isNoContent());

        verify(reviewService).delete(TEST_PRODUCT_ID);
        verifyNoMoreInteractions(reviewService);
    }

    private String mapPayload(Object object) {
        String payload = null;
        try {
            payload = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail();
        }
        return payload;
    }
}