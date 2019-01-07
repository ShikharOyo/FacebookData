package com.hackathon.Restfb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestfbApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfbApplication.class, args);
	}

}

