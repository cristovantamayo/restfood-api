package com.cristovantamayo.restfoodapi.domains.cidade.exception;

import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public CidadeNaoEncontradaException(Long cidadeId) {
		this(String.format("O ID %d informado para Cidade não existe.", cidadeId));
	}
}
