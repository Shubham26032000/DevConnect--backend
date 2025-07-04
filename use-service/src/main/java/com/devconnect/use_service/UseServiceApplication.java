package com.devconnect.use_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class UseServiceApplication {
	
	@Value("${my.prop}")
	String name;
	
	@GetMapping("/get")
	public String get() {
		StringBuilder sb = new StringBuilder();
		sb.append("<h2 color='red' >").append(name).append("</h2>");
		return sb.toString();
	}

	public static void main(String[] args) {
		SpringApplication.run(UseServiceApplication.class, args);
		
	}
	
}
