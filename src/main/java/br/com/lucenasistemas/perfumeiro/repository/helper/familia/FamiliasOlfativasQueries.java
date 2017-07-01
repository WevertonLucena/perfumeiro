package br.com.lucenasistemas.perfumeiro.repository.helper.familia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lucenasistemas.perfumeiro.model.FamiliaOlfativa;
import br.com.lucenasistemas.perfumeiro.repository.filter.FamiliaOlfativaFilter;



public interface FamiliasOlfativasQueries {
	
	public Page<FamiliaOlfativa> filtrar(FamiliaOlfativaFilter filtro, Pageable pageable);
	
}
