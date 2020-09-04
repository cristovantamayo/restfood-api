package com.cristovantamayo.restfoodapi.domains.formapagamento.repository;

import java.util.List;

import com.cristovantamayo.restfoodapi.domains.formapagamento.model.FormaPagamento;

public interface FormaPagamentoRepository {
	List<FormaPagamento> getAll();
	FormaPagamento findById(Long id);
	FormaPagamento save(FormaPagamento formaPagamento);
	void remove(FormaPagamento formaPagamento);

}
