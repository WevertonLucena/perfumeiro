package br.com.lucenasistemas.perfumeiro.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lucenasistemas.perfumeiro.model.Perfume;
import br.com.lucenasistemas.perfumeiro.repository.Perfumes;
import br.com.lucenasistemas.perfumeiro.service.exception.ImpossivelExcluirEntidadeException;
import br.com.lucenasistemas.perfumeiro.storage.FotoStorage;

@Service
public class PerfumeService {

	@Autowired
	private Perfumes perfumes;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Transactional
	public void salvar(Perfume perfume){
		perfumes.save(perfume);
	}
	
	@Transactional
	public void excluir(Perfume perfume) {
		try{
			String foto = perfume.getFoto();
			perfumes.delete(perfume);
			perfumes.flush();
			if(perfume.temFoto())
				fotoStorage.excluir(foto);
		} catch(PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar. Este Perfume já foi utilizado!");
		}
	}
}
