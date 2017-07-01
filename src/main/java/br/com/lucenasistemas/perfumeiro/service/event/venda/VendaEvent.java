package br.com.lucenasistemas.perfumeiro.service.event.venda;

import br.com.lucenasistemas.perfumeiro.model.Venda;

public class VendaEvent {

	private Venda venda;

	public VendaEvent(Venda venda) {
		this.venda = venda;
	}

	public Venda getVenda() {
		return venda;
	}

}
