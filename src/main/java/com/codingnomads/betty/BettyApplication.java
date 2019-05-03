package com.codingnomads.betty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.codingnomads.betty.logic.interfaces")
public class BettyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BettyApplication.class, args);
	}
}
