package com.cristovantamayo.restfoodapi.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.cristovantamayo.restfoodapi.RestfoodApiApplication;
import com.cristovantamayo.restfoodapi.domain.model.Cozinha;
import com.cristovantamayo.restfoodapi.domain.repository.CozinhaRepository;

public class ConsultaCozinhaMain {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(RestfoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		List<Cozinha> cozinhas= cozinhaRepository.getAll();
		
		for(Cozinha cozinha: cozinhas) {
			System.out.println(cozinha.getNome());
		}
				
	}
}
