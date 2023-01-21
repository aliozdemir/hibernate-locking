package com.ozdemir.hibernatelocking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HibernateLockingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateLockingApplication.class, args);
	}

}
