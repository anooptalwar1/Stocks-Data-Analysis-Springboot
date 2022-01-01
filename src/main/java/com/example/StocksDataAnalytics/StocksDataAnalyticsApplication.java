package com.example.StocksDataAnalytics;

import com.example.StocksDataAnalytics.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class StocksDataAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocksDataAnalyticsApplication.class, args);
	}

}
