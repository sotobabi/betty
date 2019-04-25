package com.codingnomads.betty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BettyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.out.println("--------------------------------->");
		System.out.println(System.getenv("DATABASE_USERNAME"));
		SpringApplication.run(BettyApplication.class, args);
	}
}
