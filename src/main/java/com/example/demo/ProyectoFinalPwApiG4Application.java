package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoFinalPwApiG4Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalPwApiG4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("OK");
	}

}
