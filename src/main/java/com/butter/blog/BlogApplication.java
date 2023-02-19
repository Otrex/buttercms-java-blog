package com.butter.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.butter.blog.controllers", "com.butter.blog.services"})
public class BlogApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}