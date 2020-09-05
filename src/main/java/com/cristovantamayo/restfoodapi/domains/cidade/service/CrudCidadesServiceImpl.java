package com.cristovantamayo.restfoodapi.domains.cidade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristovantamayo.restfoodapi.domains.cidade.model.Cidade;
import com.cristovantamayo.restfoodapi.domains.cidade.repository.CidadeRepository;
import com.cristovantamayo.restfoodapi.domains.estado.model.Estado;
import com.cristovantamayo.restfoodapi.domains.estado.repository.EstadoRepository;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

@Service
class CrudCidadesServiceImpl implements CrudCidadesService {
	
	@Autowired
	CidadeRepository repository;
	
	@Autowired
	EstadoRepository repositoryEstado;

	@Override
	public List<Cidade> listar() {
		return repository.findAll();
	}

	@Override
	public Optional<Cidade> buscar(Long cidadeId) {
		return repository.findById(cidadeId);
	}

	@Override
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = repositoryEstado.findById(estadoId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("O ID %d informado para Estado não existe.", estadoId)));
				
		cidade.setEstado(estado);		
		return repository.save(cidade);
	}

	@Override
	public void excluir(Long cidadeId) {
		repository.findById(cidadeId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("O ID %d informado para Cidade não existe.", cidadeId)));
		
		repository.deleteById(cidadeId);
	}

}
