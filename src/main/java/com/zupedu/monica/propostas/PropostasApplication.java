package com.zupedu.monica.propostas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PropostasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostasApplication.class, args);
	}

}
