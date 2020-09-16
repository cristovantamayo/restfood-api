package com.cristovantamayo.restfoodapi.domains.cozinha.exception;

import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public CozinhaNaoEncontradaException(Long cozinhaId) {
		this(String.format("O ID %d informado para Cozinha não existe.", cozinhaId));
	}
}
