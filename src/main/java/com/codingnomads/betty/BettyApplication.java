package com.codingnomads.betty;

import com.codingnomads.betty.logic.services.TwitterService;
import com.codingnomads.betty.presentation.controller.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import twitter4j.TwitterException;

@SpringBootApplication
public class BettyApplication extends SpringBootServletInitializer {

	@Autowired
	private TwitterService twitterService;

	public static void main(String[] args) throws TwitterException {
		SpringApplication.run(BettyApplication.class, args);

		Console console = new Console();

		console.displayService();

	}
}
