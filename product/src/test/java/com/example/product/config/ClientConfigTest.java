package com.example.product.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@Tag("unit")
@SpringBootTest(classes = ClientConfig.class)
public class ClientConfigTest {
  @Autowired
  private RestTemplate restTemplate;

  @Test
  public void loadContext() {
    assertNotNull(restTemplate);
  }

}
