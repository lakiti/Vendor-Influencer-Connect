package com.vendorinfluencer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class VendorInfluencerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorInfluencerApplication.class, args);
		System.out.println("Hi from main");
	}

}
