package com.cristovantamayo.restfoodapi.domains.restaurante.exception;

import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format("O ID %d informado para Restaurante não existe.", restauranteId));
	}
}
