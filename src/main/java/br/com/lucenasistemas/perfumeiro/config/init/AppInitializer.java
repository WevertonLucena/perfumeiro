package br.com.lucenasistemas.perfumeiro.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.lucenasistemas.perfumeiro.config.JPAConfig;
import br.com.lucenasistemas.perfumeiro.config.MailConfig;
import br.com.lucenasistemas.perfumeiro.config.S3Config;
import br.com.lucenasistemas.perfumeiro.config.SecurityConfig;
import br.com.lucenasistemas.perfumeiro.config.ServiceConfig;
import br.com.lucenasistemas.perfumeiro.config.WebConfig;


public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { JPAConfig.class,ServiceConfig.class,SecurityConfig.class, S3Config.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{ WebConfig.class, MailConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		HttpPutFormContentFilter contentFilter = new HttpPutFormContentFilter();
		
		return new Filter[] { characterEncodingFilter, contentFilter };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.setInitParameter("spring.profiles.default", "local");
	}

}
