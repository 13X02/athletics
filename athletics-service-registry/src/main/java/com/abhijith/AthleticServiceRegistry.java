package com.abhijith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AthleticServiceRegistry {

	public static void main(String[] args) {
		SpringApplication.run(AthleticServiceRegistry.class, args);
	}

}
