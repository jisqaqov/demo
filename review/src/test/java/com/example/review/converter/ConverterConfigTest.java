package com.example.review.converter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.review.converter.ConverterConfig;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Tag("unit")
@SpringBootTest(classes = {ConverterConfig.class})
public class ConverterConfigTest {
  @Autowired
  private ModelMapper modelMapper;

  @Test
  public void loadContext() {
    assertNotNull(modelMapper);
  }

}
