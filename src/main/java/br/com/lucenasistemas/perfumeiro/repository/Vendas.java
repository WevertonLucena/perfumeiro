package br.com.lucenasistemas.perfumeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucenasistemas.perfumeiro.model.Venda;
import br.com.lucenasistemas.perfumeiro.repository.helper.venda.VendasQueries;


public interface Vendas extends JpaRepository<Venda, Long>, VendasQueries {

}
