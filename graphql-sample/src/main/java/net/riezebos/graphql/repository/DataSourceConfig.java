package net.riezebos.graphql.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

  @Bean
  public MockDataSource getMockRepository() {
    return new MockDataSource();
  }

}
