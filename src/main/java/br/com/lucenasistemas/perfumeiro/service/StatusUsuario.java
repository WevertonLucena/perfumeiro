package br.com.lucenasistemas.perfumeiro.service;

import br.com.lucenasistemas.perfumeiro.repository.Usuarios;

public enum StatusUsuario {

	ATIVAR {
		@Override
		public void executar(Long[] ids, Usuarios usuarios) {
			usuarios.findByIdIn(ids).forEach(u -> u.setAtivo(true));
		}
	},
	
	DESATIVAR {
		@Override
		public void executar(Long[] ids, Usuarios usuarios) {
			usuarios.findByIdIn(ids).forEach(u -> u.setAtivo(false));
		}
	};
	
	public abstract void executar(Long[] ids, Usuarios usuarios);
	
}
