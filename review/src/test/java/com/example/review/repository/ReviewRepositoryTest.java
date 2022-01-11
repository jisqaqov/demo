package com.example.review.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.review.model.entity.ReviewEntity;
import com.example.review.repostiory.ReviewRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@Sql(scripts = "classpath:/data_test_H2.sql")
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void findAll() {
        List<ReviewEntity> reviews = reviewRepository.findAll();
        assertEquals(2, reviews.size());
    }


}
