package br.com.lucenasistemas.perfumeiro.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {

	private Page<T> page;
	private UriComponentsBuilder uriBuilder;
	public static final int MAX_PAGE_ITEM_DISPLAY = 3;
	public int inicio;

	public PageWrapper(Page<T> page, HttpServletRequest request) {
		this.page = page;
		//this.uriBuilder = ServletUriComponentsBuilder.fromRequest(request);
		String url = request.getRequestURL().append(
				request.getQueryString() != null ? "?" + 
						request.getQueryString() : "")
				.toString().replaceAll("\\+", "%20")
				.replaceAll("excluido", "");
		this.uriBuilder = ServletUriComponentsBuilder.fromHttpUrl(url);
	}
	
	public List<T> getConteudo(){
		return page.getContent();
	}
	
	public boolean isVazia(){
		return page.getContent().isEmpty();
	}
	
	public int getAtual(){
		return page.getNumber();
	}
	
	public boolean isPrimeira(){
		return page.isFirst();
	}
	
	public boolean isUltima(){
		return page.isLast();
	}
	
	public int getTotal(){
		if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
			return page.getTotalPages();
		} 
		if (getAtual() == page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY / 2) {
			return getAtual() + 1 ;
		}
		if (getAtual() + 1  == page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY / 2) {
			return getAtual() + 2 ;
		}
		if (getAtual() == 0) {
			return getAtual() + 5;
		}
		if (getAtual() <= page.getTotalPages() - 1) {
			return getAtual() +3;
		}
		return page.getTotalPages();
		
	}
	
	public String urlParaPagina(int pagina){
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}
	
	public String urlOrdenada(String propriedade){
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder
				.fromUriString(uriBuilder.build(true).encode().toUriString());
		
		String valorSort = String.format("%s,%s", propriedade, inverterDirecao(propriedade));
		
		return uriBuilderOrder.replaceQueryParam("sort", valorSort).build(true).encode().toUriString();
	}
	
	public String inverterDirecao(String propriedade){
		String direcao = "asc";
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		if (order != null) {
			direcao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}
		
		return direcao;
	}
	
	public boolean descendente(String propriedade) {
		return inverterDirecao(propriedade).equals("asc");
	}
	
	public boolean ordenada(String propriedade) {
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null; 
		
		if (order == null) {
			return false;
		}
		
		return page.getSort().getOrderFor(propriedade) != null ? true : false;
	}

	public int getInicio() {
		if(getAtual() >= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY  / 2)
			return getAtual() - 1;
		return 1;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	
	
}