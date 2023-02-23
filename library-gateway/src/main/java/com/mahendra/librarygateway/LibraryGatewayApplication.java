package com.mahendra.librarygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class LibraryGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(LibraryGatewayApplication.class, args);
	}

}
