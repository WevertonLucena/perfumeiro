package br.com.lucenasistemas.perfumeiro.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.lucenasistemas.perfumeiro.service.PerfumeService;
import br.com.lucenasistemas.perfumeiro.storage.FotoStorage;

@Configuration
@ComponentScan(basePackageClasses = { PerfumeService.class, FotoStorage.class })
public class ServiceConfig {

}
