package com.ott;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = "com.ott.entity")
@ComponentScan(basePackages = "com.ott")
@SpringBootApplication
public class YongYongOttProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(YongYongOttProjectApplication.class, args);
	}
	
}
