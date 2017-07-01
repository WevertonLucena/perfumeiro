package br.com.lucenasistemas.perfumeiro.service.event.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.lucenasistemas.perfumeiro.model.ItemVenda;
import br.com.lucenasistemas.perfumeiro.model.Perfume;
import br.com.lucenasistemas.perfumeiro.repository.Perfumes;


@Component
public class VendaListener {

	@Autowired
	private Perfumes perfumes;
	
	@EventListener
	public void vendaEmitida(VendaEvent vendaEvent) {
		for (ItemVenda item : vendaEvent.getVenda().getItens()) {
			Perfume perfume= perfumes.findOne(item.getPerfume().getId());
			perfume.setEstoque(perfume.getEstoque() - item.getQuantidade());
			perfumes.save(perfume);
		}
	}
	
}