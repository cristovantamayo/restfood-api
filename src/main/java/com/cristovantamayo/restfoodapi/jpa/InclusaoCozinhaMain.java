package com.cristovantamayo.restfoodapi.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.cristovantamayo.restfoodapi.RestfoodApiApplication;
import com.cristovantamayo.restfoodapi.domain.model.Cozinha;
import com.cristovantamayo.restfoodapi.domain.repository.CozinhaRepository;

public class InclusaoCozinhaMain {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext =  new SpringApplicationBuilder(RestfoodApiApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Brasileira");
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Japonesa");
		
		Cozinha novaCozinha1 = cozinhaRepository.save(cozinha1);		 
		Cozinha novaCozinha2 = cozinhaRepository.save(cozinha2);
		
		System.out.printf("%d - %s\n", novaCozinha1.getId(), novaCozinha1.getNome());
		System.out.printf("%d - %s\n", novaCozinha2.getId(), novaCozinha2.getNome());
		
				
	}
}
