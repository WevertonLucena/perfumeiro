package br.com.lucenasistemas.perfumeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucenasistemas.perfumeiro.model.Cliente;
import br.com.lucenasistemas.perfumeiro.repository.helper.cliente.ClientesQueries;

public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries {

	Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

	List<Cliente> findByNomeStartingWithIgnoreCase(String nome);

}
