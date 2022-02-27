package se.wordspark.database.repository;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
class InfrastructureConfiguration {

  @Bean
  public H2ConnectionFactory connectionFactory() {
    return new H2ConnectionFactory(
        H2ConnectionConfiguration.builder()
            .url("mem:~/db;MODE=PostgreSQL;DB_CLOSE_DELAY=-1")
            .build());
  }
}
