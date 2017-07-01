package br.com.lucenasistemas.perfumeiro.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lucenasistemas.perfumeiro.model.Cliente;
import br.com.lucenasistemas.perfumeiro.repository.filter.ClienteFilter;




public interface ClientesQueries {

	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);
	
}
