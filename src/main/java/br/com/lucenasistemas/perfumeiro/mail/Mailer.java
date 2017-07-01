package br.com.lucenasistemas.perfumeiro.mail;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.lucenasistemas.perfumeiro.model.ItemVenda;
import br.com.lucenasistemas.perfumeiro.model.Perfume;
import br.com.lucenasistemas.perfumeiro.model.Venda;
import br.com.lucenasistemas.perfumeiro.storage.FotoStorage;


@Component
public class Mailer {
	
	private static Logger logger = LoggerFactory.getLogger(Mailer.class);

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Async
	public void enviar(Venda venda) {
		Context context = new Context(new Locale("pt", "BR"));
		
		context.setVariable("venda", venda);
		context.setVariable("logo", "logo");
		
		Map<String, String> fotos = new HashMap<>();
		boolean adicionarMockCerveja = false;
		for (ItemVenda item : venda.getItens()) {
			Perfume perfume= item.getPerfume();
			if (perfume.temFoto()) {
				String cid = "foto-" + perfume.getId();
				context.setVariable(cid, cid);
				
				fotos.put(cid, perfume.getFoto() + "|" + perfume.getContentType());
			} else {
				adicionarMockCerveja = true;
				context.setVariable("mockPerfume", "mockPerfume");
			}
		}
		
		try {
			String email = thymeleaf.process("mail/resumoVenda", context);
			
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("teste@lucenasistemas.com");
			helper.setTo(venda.getCliente().getEmail());
			helper.setSubject(String.format("LucenaSistemas - Venda nº %d", venda.getId()));
			helper.setText(email, true);
			
			helper.addInline("logo", new ClassPathResource("static/images/logo-gray.png"));
			
			if (adicionarMockCerveja) {
				helper.addInline("mockPerfume", new ClassPathResource("static/images/perfume-mock.png"));
			}
			
			for (String cid : fotos.keySet()) {
				String[] fotoContentType = fotos.get(cid).split("\\|");
				String foto = fotoContentType[0];
				String contentType = fotoContentType[1];
				byte[] arrayFoto = fotoStorage.recuperarThumbnail(foto);
				helper.addInline(cid, new ByteArrayResource(arrayFoto), contentType);
			}
		
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Erro enviando e-mail", e);
		}
	}
		
		/*
		System.out.println("enviando email");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("email enviado");
		*/
}
	
