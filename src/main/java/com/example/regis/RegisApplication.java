package com.example.regis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisApplication.class, args);
		System.out.printf("started");
	}
}
