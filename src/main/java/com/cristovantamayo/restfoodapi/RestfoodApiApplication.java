package com.cristovantamayo.restfoodapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.cristovantamayo.restfoodapi.commons.repository.CustomJpaRepositoruImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoruImpl.class)
public class RestfoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfoodApiApplication.class, args);
	}

}
