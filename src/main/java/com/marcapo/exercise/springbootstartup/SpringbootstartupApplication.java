package com.marcapo.exercise.springbootstartup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan("com.marcapo.exercise.springbootstartup")
public class SpringbootstartupApplication
{

	public static void main(final String[] args)
	{
		SpringApplication.run(SpringbootstartupApplication.class, args);
	}

}
