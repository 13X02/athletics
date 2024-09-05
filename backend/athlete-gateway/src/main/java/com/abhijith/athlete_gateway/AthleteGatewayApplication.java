package com.abhijith.athlete_gateway;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.netty.http.client.HttpClient;

@SpringBootApplication
@CrossOrigin(origins = "*") // Allow CORS from localhost:3000
public class AthleteGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AthleteGatewayApplication.class, args);
	}

	@Bean
	public HttpClient httpClient() {
		return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
	}
}
