package br.com.lucenasistemas.perfumeiro.service.exception;

public class NomeClienteJaCadastradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NomeClienteJaCadastradoException(String message) {
		super(message);
	}
}
