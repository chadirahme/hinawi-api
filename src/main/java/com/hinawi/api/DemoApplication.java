package com.hinawi.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	//private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		//System.out.println("Current Directory = " + System.getProperty("user.dir"));
		SpringApplication.run(DemoApplication.class, args);
		//logger.info("just a test info log");
	}

}

