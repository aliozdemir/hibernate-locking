package com.ozdemir.hibernatelocking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Configuration
@EnableRetry
public class HibernateLockingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateLockingApplication.class, args);
	}

}
