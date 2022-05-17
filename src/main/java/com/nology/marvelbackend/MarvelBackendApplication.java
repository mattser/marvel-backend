package com.nology.marvelbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class MarvelBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarvelBackendApplication.class, args);
	}

}
