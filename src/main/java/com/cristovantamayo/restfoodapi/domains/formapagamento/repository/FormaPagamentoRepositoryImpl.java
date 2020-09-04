package com.cristovantamayo.restfoodapi.domains.formapagamento.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cristovantamayo.restfoodapi.domains.formapagamento.model.FormaPagamento;

@Component
class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<FormaPagamento> getAll(){
		return manager.createQuery("from Forma_Pagamento", FormaPagamento.class)
				.getResultList();
	}
	
	@Override
	public FormaPagamento findById(Long id) {
		return manager.find(FormaPagamento.class, id);
	}
	
	@Transactional
	@Override
	public FormaPagamento save(FormaPagamento formaPagamento) {
		return manager.merge(formaPagamento);
	}
	
	@Transactional
	@Override
	public void remove(FormaPagamento formaPagamento) {
		formaPagamento = findById(formaPagamento.getId());
		manager.remove(formaPagamento);
	}

}
