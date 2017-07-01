package br.com.lucenasistemas.perfumeiro.repository.helper.venda;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lucenasistemas.perfumeiro.dto.VendaMes;
import br.com.lucenasistemas.perfumeiro.dto.VendaOrigem;
import br.com.lucenasistemas.perfumeiro.model.Venda;
import br.com.lucenasistemas.perfumeiro.repository.filter.VendaFilter;


public interface VendasQueries {

	public Page<Venda> filtrar(VendaFilter filtro, Pageable pageable);
	
	public Venda buscarComItens(Long id);
	
	public BigDecimal valorTotalNoAno();
	public BigDecimal valorTotalNoMes();
	public BigDecimal valorTicketMedioNoAno();
	
	public List<VendaMes> totalPorMes();
	public List<VendaOrigem> totalPorOrigem();
	
}
