package br.com.lucenasistemas.perfumeiro.model;


public enum Origem {

	NACIONAL("Nacional"),
	IMPORTADO("Importado");
	
	private String descricao;
	
	Origem(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() { 
		return descricao;
	}
	
}
