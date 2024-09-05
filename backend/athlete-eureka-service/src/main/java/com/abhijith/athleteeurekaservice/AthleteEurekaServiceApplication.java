package com.abhijith.athleteeurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AthleteEurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AthleteEurekaServiceApplication.class, args);
	}

}
