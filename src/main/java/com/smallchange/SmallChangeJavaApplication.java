package com.smallchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SmallChangeJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmallChangeJavaApplication.class, args);
	}

}
