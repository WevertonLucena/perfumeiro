package br.com.lucenasistemas.perfumeiro.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.lucenasistemas.perfumeiro.model.Usuario;
import br.com.lucenasistemas.perfumeiro.repository.filter.UsuarioFilter;


public interface UsuariosQueries {

	public Optional<Usuario> porEmailEAtivo(String email);

	public List<String> permissoes(Usuario usuario);
	
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);
	
	public Usuario buscarComGrupos(Long id);
	
}
