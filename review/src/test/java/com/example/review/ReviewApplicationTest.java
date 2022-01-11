package com.example.review;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ReviewApplicationTest {

  @Autowired
  private ApplicationContext ctx;

  @Test
  public void testCtx() {
    assertNotNull(ctx);
  }

  @Test
  public void testStartApp() {
    assertDoesNotThrow(() -> ReviewApplication.main(new String[] {}));
  }

}
