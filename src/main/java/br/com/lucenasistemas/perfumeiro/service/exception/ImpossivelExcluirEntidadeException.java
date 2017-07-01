package br.com.lucenasistemas.perfumeiro.service.exception;

public class ImpossivelExcluirEntidadeException extends RuntimeException {
	public static final long serialVersionUID = 1L;
	
	public ImpossivelExcluirEntidadeException(String msg){
		super(msg);
	}
}
