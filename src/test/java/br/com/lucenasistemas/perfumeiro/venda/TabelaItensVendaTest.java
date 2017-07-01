package br.com.lucenasistemas.perfumeiro.venda;

import static org.junit.Assert.*;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.com.lucenasistemas.perfumeiro.model.Perfume;
import br.com.lucenasistemas.perfumeiro.session.TabelaItensVenda;


public class TabelaItensVendaTest {

	private TabelaItensVenda tabelaItensVenda;
	
	@Before
	public void setUp() {
		this.tabelaItensVenda = new TabelaItensVenda("1");
	}
	
	@Test
	public void deveCalcularValorTotalSemItens() throws Exception {
		assertEquals(BigDecimal.ZERO, tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem() throws Exception {
		Perfume perfume = new Perfume();
		BigDecimal valor = new BigDecimal("8.90");
		perfume.setValor(valor);
		
		tabelaItensVenda.adicionarItem(perfume, 1);
		
		assertEquals(valor, tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComVariosItens() throws Exception {
		Perfume c1 = new Perfume();
		c1.setId(1L);
		BigDecimal v1 = new BigDecimal("8.90");
		c1.setValor(v1);
		
		Perfume c2 = new Perfume();
		c2.setId(2L);
		BigDecimal v2 = new BigDecimal("4.99");
		c2.setValor(v2);
		
		tabelaItensVenda.adicionarItem(c1, 1);
		tabelaItensVenda.adicionarItem(c2, 2);
		
		assertEquals(new BigDecimal("18.88"), tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveManterTamanhoDaListaParaMesmasCervejas() throws Exception {
		Perfume c1 = new Perfume();
		c1.setId(1L);
		c1.setValor(new BigDecimal("4.50"));
		
		tabelaItensVenda.adicionarItem(c1, 1);
		tabelaItensVenda.adicionarItem(c1, 1);
		
		assertEquals(1, tabelaItensVenda.total());
		assertEquals(new BigDecimal("9.00"), tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveAlterarQuantidadeDoItem() throws Exception {
		Perfume c1 = new Perfume();
		c1.setId(1L);
		c1.setValor(new BigDecimal("4.50"));
		
		tabelaItensVenda.adicionarItem(c1, 1);
		tabelaItensVenda.alterarQuantidadeItens(c1, 3);
		
		assertEquals(1, tabelaItensVenda.total());
		assertEquals(new BigDecimal("13.50"), tabelaItensVenda.getValorTotal());
	}
	
	@Test
	public void deveExcluirItem() throws Exception {
		Perfume c1 = new Perfume();
		c1.setId(1L);
		c1.setValor(new BigDecimal("8.90"));
		
		Perfume c2 = new Perfume();
		c2.setId(2L);
		c2.setValor(new BigDecimal("4.99"));
		
		Perfume c3 = new Perfume();
		c3.setId(3L);
		c3.setValor(new BigDecimal("2.00"));
		
		tabelaItensVenda.adicionarItem(c1, 1);
		tabelaItensVenda.adicionarItem(c2, 2);
		tabelaItensVenda.adicionarItem(c3, 1);
		
		tabelaItensVenda.excluirItem(c2);
		
		assertEquals(2, tabelaItensVenda.total());
		assertEquals(new BigDecimal("10.90"), tabelaItensVenda.getValorTotal());
	}
	
}