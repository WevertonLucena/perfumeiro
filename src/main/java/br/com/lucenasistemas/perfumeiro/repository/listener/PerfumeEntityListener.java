package br.com.lucenasistemas.perfumeiro.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.lucenasistemas.perfumeiro.model.Perfume;
import br.com.lucenasistemas.perfumeiro.storage.FotoStorage;

public class PerfumeEntityListener {

	@Autowired
	private FotoStorage fotoStorage;
	
	@PostLoad
	public void postLoad(final Perfume perfume) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		perfume.setUrlFoto(fotoStorage.getUrl(perfume.getFotoOuMock()));
		perfume.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + perfume.getFotoOuMock()));
	}
}
