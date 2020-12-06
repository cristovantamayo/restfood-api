package com.cristovantamayo.restfoodapi.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {
	
	private int numeroMultiplo;
	
	@Override
	public void initialize(Multiplo constraintAnnotation) {
		this.numeroMultiplo = constraintAnnotation.numero();
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		boolean valido = true;
		
		if(value != null) {
			BigDecimal valorDecimal = BigDecimal.valueOf(value.doubleValue());
			BigDecimal multiploDecimal = BigDecimal.valueOf(this.numeroMultiplo);
			BigDecimal resto = valorDecimal.remainder(multiploDecimal);
			
			valido = BigDecimal.ZERO.compareTo(resto) == 0;
		}
		
		return valido;
	}

}
