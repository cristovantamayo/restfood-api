package com.cristovantamayo.restfoodapi.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.cristovantamayo.restfoodapi.RestfoodApiApplication;
import com.cristovantamayo.restfoodapi.domain.model.Cozinha;
import com.cristovantamayo.restfoodapi.domain.repository.CozinhaRepository;

public class ExclusaoCozinhaMain {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(RestfoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);
		
		cozinhaRepository.remove(cozinha);
		
				
	}
}
