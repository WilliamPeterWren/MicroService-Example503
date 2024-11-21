package com.trannubichthai.product_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication(scanBasePackages = {
		"com.trannubichthai.product_service",
		"com.trannubichthai.amqp"
})
// @EnableElasticsearchRepositories(basePackages =
// "com.trannubichthai.product_service.repository")
// @SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
