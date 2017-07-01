package br.com.lucenasistemas.perfumeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucenasistemas.perfumeiro.model.Cidade;
import br.com.lucenasistemas.perfumeiro.model.Estado;
import br.com.lucenasistemas.perfumeiro.repository.helper.cidade.CidadesQueries;


public interface Cidades extends JpaRepository<Cidade, Long>, CidadesQueries {

	public List<Cidade> findByEstadoId(Long codigoEstado);

	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);
	
}
