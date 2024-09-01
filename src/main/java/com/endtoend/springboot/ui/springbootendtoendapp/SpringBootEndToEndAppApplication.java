package com.endtoend.springboot.ui.springbootendtoendapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootEndToEndAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEndToEndAppApplication.class, args);
	}

}
