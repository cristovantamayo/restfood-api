package com.cristovantamayo.restfoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-use", "Entidade em uso"),
	SOLICITACAO_IMPROPRIA("/solicitacao-impropria", "Solicitação Imprópria ");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "http://api.restfood.local" + path;
		this.title = title;
	}
}
