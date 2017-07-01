package br.com.lucenasistemas.perfumeiro.repository.helper.perfume;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lucenasistemas.perfumeiro.dto.PerfumeDTO;
import br.com.lucenasistemas.perfumeiro.dto.ValorItensEstoque;
import br.com.lucenasistemas.perfumeiro.model.Perfume;
import br.com.lucenasistemas.perfumeiro.repository.filter.PerfumeFilter;;

public interface PerfumesQueries {
	public Page<Perfume> filtrar(PerfumeFilter filtro, Pageable pageable);
	
	public List<PerfumeDTO> porSkuOuNome(String skuOuNome);
	
	public ValorItensEstoque valorItensEstoque();
}
