package br.com.lucenasistemas.perfumeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucenasistemas.perfumeiro.model.FamiliaOlfativa;
import br.com.lucenasistemas.perfumeiro.repository.helper.familia.FamiliasOlfativasQueries;

@Repository
public interface FamiliasOlfativas extends JpaRepository<FamiliaOlfativa,Long>, FamiliasOlfativasQueries {

	public Optional<FamiliaOlfativa> findByNomeIgnoreCase(String nome);

}
