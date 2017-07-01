package br.com.lucenasistemas.perfumeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucenasistemas.perfumeiro.model.Estado;


public interface Estados extends JpaRepository<Estado, Long> {

}
