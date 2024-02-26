package com.alternova.streaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
		"com.alternova.auth.persistence.entity",
		"com.alternova.streaming.persistence.entity" })
@ComponentScan(basePackages = {
		"com.alternova.auth",
		"com.alternova.streaming" })
@EnableJpaRepositories(basePackages = {
		"com.alternova.auth.persistence.repository",
		"com.alternova.streaming.persistence.repository"
})
public class StreamingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamingServiceApplication.class, args);
	}

}
