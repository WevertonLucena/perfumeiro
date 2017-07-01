package br.com.lucenasistemas.perfumeiro.service.exception;

public class NomeFamiliaJaCadastradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NomeFamiliaJaCadastradoException(String message) {
		super(message);
	}
}
