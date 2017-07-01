package br.com.lucenasistemas.perfumeiro.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.lucenasistemas.perfumeiro.model.ItemVenda;
import br.com.lucenasistemas.perfumeiro.model.Perfume;



@SessionScope
@Component
public class TabelaItensVenda {
	private String uuid;
	private List<ItemVenda> itens = new ArrayList<>();
	
	public TabelaItensVenda(String uuid) {
		this.uuid = uuid;
	}

	public BigDecimal getValorTotal() {
		return itens.stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Perfume perfume, Integer quantidade) {
		Optional<ItemVenda> itemVendaOptional = buscarItemPorPerfume(perfume);
		
		ItemVenda itemVenda = null;
		if (itemVendaOptional.isPresent()) {
			itemVenda = itemVendaOptional.get();
			itemVenda.setQuantidade(itemVenda.getQuantidade() + quantidade);
		} else {
			itemVenda = new ItemVenda();
			itemVenda.setPerfume(perfume);
			itemVenda.setQuantidade(quantidade);
			itemVenda.setValorUnitario(perfume.getValor());
			itens.add(0, itemVenda);
		}
	}
	
	public void alterarQuantidadeItens(Perfume perfume, Integer quantidade) {
		ItemVenda itemVenda = buscarItemPorPerfume(perfume).get();
		itemVenda.setQuantidade(quantidade);
	}
	
	public void excluirItem(Perfume perfume) {
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getPerfume().equals(perfume))
				.findAny().getAsInt();
		itens.remove(indice);
	}
	
	public int total() {
		return itens.size();
	}

	public List<ItemVenda> getItens() {
		return itens;
	}
	
	private Optional<ItemVenda> buscarItemPorPerfume(Perfume perfume) {
		return itens.stream()
				.filter(i -> i.getPerfume().equals(perfume))
				.findAny();
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaItensVenda other = (TabelaItensVenda) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}
