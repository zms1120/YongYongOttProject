package com.ott;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.ott.entity")
@SpringBootApplication
public class YongYongOttProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(YongYongOttProjectApplication.class, args);
	}
	
}
