package com.app.FixIt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling

public class FixItApplication {

	public static void main(String[] args) {
		SpringApplication.run(FixItApplication.class, args);
	}
	
}
