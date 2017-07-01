package br.com.lucenasistemas.perfumeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lucenasistemas.perfumeiro.model.Grupo;


public interface Grupos extends JpaRepository<Grupo, Long> {

}
