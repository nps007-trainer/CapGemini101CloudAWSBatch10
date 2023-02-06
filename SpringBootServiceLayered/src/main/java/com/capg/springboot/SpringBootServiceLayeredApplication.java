package com.capg.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
// Enabling swagger documentation and UI
@SpringBootApplication
@EnableSwagger2
public class SpringBootServiceLayeredApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootServiceLayeredApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
