package com.cristovantamayo.restfoodapi.domains.cidade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cristovantamayo.restfoodapi.domains.cidade.model.Cidade;
import com.cristovantamayo.restfoodapi.domains.cidade.repository.CidadeRepository;
import com.cristovantamayo.restfoodapi.domains.estado.model.Estado;
import com.cristovantamayo.restfoodapi.domains.estado.service.CrudEstadoService;
import com.cristovantamayo.restfoodapi.exception.EntidadeEmUsoException;
import com.cristovantamayo.restfoodapi.exception.EntidadeNaoEncontradaException;

@Service
class CrudCidadesServiceImpl implements CrudCidadesService {
	
	private static final String MSG_CIDADE_EM_USO =
			"A Cidade de código %d não pode ser removida, pois está em uso.";
	private static final String MSG_CIDADE_NAO_ENCONTRADA =
			"O ID %d informado para Cidade não existe.";

	@Autowired
	CidadeRepository repository;
	
	@Autowired
	CrudEstadoService servicoEstado;

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
		Estado estado = servicoEstado.getOrFail(estadoId);
		cidade.setEstado(estado);		
		return repository.save(cidade);
	}

	@Override
	public void excluir(Long cidadeId) {
		try {
			repository.deleteById(cidadeId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
				String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}

	@Override
	public Cidade getOrFail(Long cidadeId) {
		return repository.findById(cidadeId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
	}

}
