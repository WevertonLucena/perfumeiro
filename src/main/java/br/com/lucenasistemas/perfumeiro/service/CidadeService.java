package br.com.lucenasistemas.perfumeiro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lucenasistemas.perfumeiro.model.Cidade;
import br.com.lucenasistemas.perfumeiro.repository.Cidades;
import br.com.lucenasistemas.perfumeiro.service.exception.NomeCidadeJaCadastradaException;


@Service
public class CidadeService {

	@Autowired
	private Cidades cidades;
	
	@Transactional
	public void salvar(Cidade cidade) {
		Optional<Cidade> cidadeExistente = cidades.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		if (cidadeExistente.isPresent()) {
			throw new NomeCidadeJaCadastradaException("Nome de cidade já cadastrado");
		}
		
		cidades.save(cidade);
	}

	
}
