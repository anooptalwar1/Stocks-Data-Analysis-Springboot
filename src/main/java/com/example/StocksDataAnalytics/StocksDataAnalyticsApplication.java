package com.example.StocksDataAnalytics;

import com.example.StocksDataAnalytics.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class StocksDataAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocksDataAnalyticsApplication.class, args);
	}

}
