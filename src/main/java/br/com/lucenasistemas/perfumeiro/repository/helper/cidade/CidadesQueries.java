package br.com.lucenasistemas.perfumeiro.repository.helper.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lucenasistemas.perfumeiro.model.Cidade;
import br.com.lucenasistemas.perfumeiro.repository.filter.CidadeFilter;


public interface CidadesQueries {

	public Page<Cidade> filtrar(CidadeFilter filtro, Pageable pageable);
	
}
