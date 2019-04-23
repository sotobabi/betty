package com.codingnomads.betty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import twitter4j.TwitterException;

@SpringBootApplication
public class BettyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws TwitterException {
		SpringApplication.run(BettyApplication.class, args);
	}
}
