package com.cristovantamayo.restfoodapi.domains.estado.exception;

import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format("O ID %d informado para Estado não existe.", estadoId));
	}
}
