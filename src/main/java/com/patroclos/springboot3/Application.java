package com.patroclos.springboot3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.patroclos.*")
public class Application {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/app");
		SpringApplication.run(Application.class, args);
	}
}
