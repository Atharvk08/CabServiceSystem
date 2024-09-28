package com.cabservice.cab_finder_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CabFinderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabFinderServiceApplication.class, args);
	}

}
