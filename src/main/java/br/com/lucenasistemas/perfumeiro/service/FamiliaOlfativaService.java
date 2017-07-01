package br.com.lucenasistemas.perfumeiro.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lucenasistemas.perfumeiro.model.FamiliaOlfativa;
import br.com.lucenasistemas.perfumeiro.repository.FamiliasOlfativas;
import br.com.lucenasistemas.perfumeiro.service.exception.ImpossivelExcluirEntidadeException;
import br.com.lucenasistemas.perfumeiro.service.exception.NomeFamiliaJaCadastradoException;

@Service
public class FamiliaOlfativaService {
	
	@Autowired
	private FamiliasOlfativas familias;
	
	@Transactional
	public FamiliaOlfativa salvar(FamiliaOlfativa familia){
		
		Optional<FamiliaOlfativa> estiloOptional = familias.findByNomeIgnoreCase(familia.getNome());
		if (estiloOptional.isPresent()) {
			throw new NomeFamiliaJaCadastradoException("Nome da família já cadastrado");
		}
		
		return familias.saveAndFlush(familia);
	}
	
	@Transactional
	public void excluir(FamiliaOlfativa familia) {
		try{
			familias.delete(familia);
			familias.flush();
		} catch(PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar. Esta família já foi utilizada!");
		}
	}

}
