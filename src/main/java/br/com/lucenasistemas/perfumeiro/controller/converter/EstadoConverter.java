package br.com.lucenasistemas.perfumeiro.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.lucenasistemas.perfumeiro.model.Estado;


public class EstadoConverter implements Converter<String, Estado>{

	@Override
	public Estado convert(String id) {
		if(StringUtils.isEmpty(id))
			return null;
		Estado estado = new Estado();
		estado.setId(Long.valueOf(id));
		return estado;
	}

}
