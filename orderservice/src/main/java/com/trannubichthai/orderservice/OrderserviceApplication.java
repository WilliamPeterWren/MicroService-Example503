package com.trannubichthai.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
		"com.trannubichthai.orderservice",
		"com.trannubichthai.amqp"
})
@EnableFeignClients(basePackages = "com.trannubichthai.orderservice.client")
public class OrderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}

}
