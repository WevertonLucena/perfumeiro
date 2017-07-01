package br.com.lucenasistemas.perfumeiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucenasistemas.perfumeiro.model.Perfume;
import br.com.lucenasistemas.perfumeiro.repository.helper.perfume.PerfumesQueries;

@Repository
public interface Perfumes  extends JpaRepository<Perfume,Long>, PerfumesQueries {
	
	public Optional<Perfume> findBySkuIgnoreCase(String sku);
}
