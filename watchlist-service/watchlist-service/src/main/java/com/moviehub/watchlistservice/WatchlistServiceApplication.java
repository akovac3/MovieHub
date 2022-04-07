package com.moviehub.watchlistservice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class WatchlistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchlistServiceApplication.class, args);
	}

	@Bean
	OpenAPI openApiConfig() {
		return new OpenAPI().info(apiInfo());
	}

	public Info apiInfo() {
		Info info = new Info();
		info.title("Watchlist microservice")
				.description("Watchlist microservice")
				.version("v1.0");
		return info;
	}

}
