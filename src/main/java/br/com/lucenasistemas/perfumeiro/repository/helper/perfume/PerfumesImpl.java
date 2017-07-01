package br.com.lucenasistemas.perfumeiro.repository.helper.perfume;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.lucenasistemas.perfumeiro.dto.PerfumeDTO;
import br.com.lucenasistemas.perfumeiro.dto.ValorItensEstoque;
import br.com.lucenasistemas.perfumeiro.model.Perfume;
import br.com.lucenasistemas.perfumeiro.repository.filter.PerfumeFilter;
import br.com.lucenasistemas.perfumeiro.repository.paginacao.PaginacaoUtil;
import br.com.lucenasistemas.perfumeiro.storage.FotoStorage;


public class PerfumesImpl implements PerfumesQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Perfume> filtrar(PerfumeFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Perfume.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Override
	public ValorItensEstoque valorItensEstoque() {
		String query = "select new br.com.lucenasistemas.perfumeiro.dto.ValorItensEstoque(sum(valor * estoque), sum(estoque)) from Perfume";
		return manager.createQuery(query, ValorItensEstoque.class).getSingleResult();
	}
	
	private Long total(PerfumeFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Perfume.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(PerfumeFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getSku())) {
				criteria.add(Restrictions.eq("sku", filtro.getSku()));
			}
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (isFamiliaPresente(filtro)) {
				criteria.add(Restrictions.eq("familia", filtro.getFamilia()));
			}

			if (filtro.getGenero() != null) {
				criteria.add(Restrictions.eq("genero", filtro.getGenero()));
			}

			if (filtro.getOrigem() != null) {
				criteria.add(Restrictions.eq("origem", filtro.getOrigem()));
			}

			if (filtro.getValorDe() != null) {
				criteria.add(Restrictions.ge("valor", filtro.getValorDe()));
			}

			if (filtro.getValorAte() != null) {
				criteria.add(Restrictions.le("valor", filtro.getValorAte()));
			}
		}
	}
	
	private boolean isFamiliaPresente(PerfumeFilter filtro) {
		return filtro.getFamilia() != null && filtro.getFamilia().getId() != null;
	}
	
	@Override
	public List<PerfumeDTO> porSkuOuNome(String skuOuNome) {
		String jpql = "select new br.com.lucenasistemas.perfumeiro.dto.PerfumeDTO(id, sku, nome, origem, valor, foto) "
				+ "from Perfume where lower(sku) like lower(:skuOuNome) or lower(nome) like lower(:skuOuNome)";
		List<PerfumeDTO> perfumesFiltrados = manager.createQuery(jpql, PerfumeDTO.class)
					.setParameter("skuOuNome", skuOuNome + "%")
					.getResultList();
		
		perfumesFiltrados.forEach(c -> c.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + c.getFoto())));
		
		return perfumesFiltrados;
	}

}