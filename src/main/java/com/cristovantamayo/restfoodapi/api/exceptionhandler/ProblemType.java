package com.cristovantamayo.restfoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-use", "Entidade em uso"),
	SOLICITACAO_IMPROPRIA("/solicitacao-impropria", "Solicitação Imprópria"),
	REQUISICAO_INCOMPREENSSIVEL("/requisicao-incompreenssivel", "Requisição incompreenssível"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro Inválido"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de Sistema"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados Inválidos");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "http://api.restfood.local" + path;
		this.title = title;
	}
}
