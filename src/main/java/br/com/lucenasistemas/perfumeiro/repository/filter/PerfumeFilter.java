package br.com.lucenasistemas.perfumeiro.repository.filter;

import java.math.BigDecimal;

import br.com.lucenasistemas.perfumeiro.model.FamiliaOlfativa;
import br.com.lucenasistemas.perfumeiro.model.Genero;
import br.com.lucenasistemas.perfumeiro.model.Origem;



public class PerfumeFilter {

	private String sku;
	private String nome;
	private FamiliaOlfativa familia;
	private Genero genero;
	private Origem origem;
	private BigDecimal valorDe;
	private BigDecimal valorAte;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public BigDecimal getValorDe() {
		return valorDe;
	}

	public void setValorDe(BigDecimal valorDe) {
		this.valorDe = valorDe;
	}

	public BigDecimal getValorAte() {
		return valorAte;
	}

	public void setValorAte(BigDecimal valorAte) {
		this.valorAte = valorAte;
	}

	public FamiliaOlfativa getFamilia() {
		return familia;
	}

	public void setFamilia(FamiliaOlfativa familia) {
		this.familia = familia;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	

}
