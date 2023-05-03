package com.example.bloglv4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class Bloglv4Application {

	public static void main(String[] args) {
		SpringApplication.run(Bloglv4Application.class, args);
	}

}
