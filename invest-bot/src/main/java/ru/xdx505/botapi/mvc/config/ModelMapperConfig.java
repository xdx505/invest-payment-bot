package ru.xdx505.botapi.mvc.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel.PUBLIC;

@Configuration
public class ModelMapperConfig {
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration()
      .setMatchingStrategy(MatchingStrategies.STANDARD)
      .setFieldMatchingEnabled(true)
      .setSkipNullEnabled(true)
      .setFieldAccessLevel(PUBLIC);

    return mapper;
  }
}
