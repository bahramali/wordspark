package se.wordspark.application;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = {"se.wordspark.application"})
@EnableR2dbcRepositories(basePackages = "se.wordspark.database.repository")
public class WordsParkApplication {

    @Value("${spring.application.name}")
    private String applicationName;

    public static void main(String[] args) {
        SpringApplication.run(WordsParkApplication.class, args);
    }

    @Bean
    public OpenAPI createOpenAPI() {
        return new OpenAPI()
            .info(new Info().title(applicationName)
                .description("List of words"));
    }

}
