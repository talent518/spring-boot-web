package com.talent518.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAutoConfiguration
@EnableAsync
@SpringBootApplication
public class SpringBootWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}
}
