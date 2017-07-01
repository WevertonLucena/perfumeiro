package br.com.lucenasistemas.perfumeiro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lucenasistemas.perfumeiro.model.Usuario;
import br.com.lucenasistemas.perfumeiro.repository.helper.usuario.UsuariosQueries;


public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries{

	public Optional<Usuario> findByEmail(String email);

	public Optional<Usuario> porEmailEAtivo(String email);
	
	public List<Usuario> findByIdIn(Long[] codigos);

	public Optional<Usuario> findByEmailOrId(String email, Long codigo);

}
