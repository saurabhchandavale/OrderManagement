package com.example.demo;

import org.springframework.boot.SpringApplication;

import com.example.AdvancedOrderSystemApplication;

public class TestAdvancedOrderSystemApplication {

	public static void main(String[] args) {
		SpringApplication.from(AdvancedOrderSystemApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
