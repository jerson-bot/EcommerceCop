package com.ecommercecop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication( exclude = DataSourceAutoConfiguration.class)
public class EcommerceCopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceCopApplication.class, args);
	}

}
