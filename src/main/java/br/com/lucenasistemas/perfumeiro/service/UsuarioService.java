package br.com.lucenasistemas.perfumeiro.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.lucenasistemas.perfumeiro.model.Usuario;
import br.com.lucenasistemas.perfumeiro.repository.Usuarios;
import br.com.lucenasistemas.perfumeiro.service.exception.EmailUsuarioJaCadastradoException;
import br.com.lucenasistemas.perfumeiro.service.exception.ImpossivelExcluirEntidadeException;
import br.com.lucenasistemas.perfumeiro.service.exception.SenhaObrigatoriaUsuarioException;


@Service
public class UsuarioService {

	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarios.findByEmailOrId(usuario.getEmail(), usuario.getId());
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado");
		}
		
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário");
		}
		
		if (usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		} else if (StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioExistente.get().getSenha());
		}
		usuario.setConfirmacaoSenha(usuario.getSenha());
		
		if (!usuario.isNovo() && usuario.getAtivo() == null) {
			usuario.setAtivo(usuarioExistente.get().getAtivo());
		}
		
		usuarios.save(usuario);
	}
	
	@Transactional
	public void alterarStatus(Long[] ids, StatusUsuario statusUsuario) {
		statusUsuario.executar(ids, usuarios);
	}
	
	@Transactional
	public void excluir(Usuario usuario) {
		try{			
			usuarios.delete(usuario);
			usuarios.flush();
		} catch(PersistenceException e){
			throw new ImpossivelExcluirEntidadeException("Impossível apagar. Este Usuário já foi utilizado!");
		}
	}
	
}