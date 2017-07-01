package br.com.lucenasistemas.perfumeiro.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.lucenasistemas.perfumeiro.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import br.com.lucenasistemas.perfumeiro.thymeleaf.processor.MenuAttributeTagProcessor;
import br.com.lucenasistemas.perfumeiro.thymeleaf.processor.MessageElementTagProcessor;
import br.com.lucenasistemas.perfumeiro.thymeleaf.processor.OrderElementTagProcessor;
import br.com.lucenasistemas.perfumeiro.thymeleaf.processor.PaginationElementTagProcessor;


public class LucenaDialect extends AbstractProcessorDialect {
	
	public LucenaDialect() {
		super("LucenaSolucoes lscolect", "lucena", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		return processadores;
	}
}
