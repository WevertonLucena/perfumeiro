package br.com.lucenasistemas.perfumeiro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lucenasistemas.perfumeiro.controller.page.PageWrapper;
import br.com.lucenasistemas.perfumeiro.dto.PerfumeDTO;
import br.com.lucenasistemas.perfumeiro.model.Genero;
import br.com.lucenasistemas.perfumeiro.model.Origem;
import br.com.lucenasistemas.perfumeiro.model.Perfume;
import br.com.lucenasistemas.perfumeiro.repository.FamiliasOlfativas;
import br.com.lucenasistemas.perfumeiro.repository.Perfumes;
import br.com.lucenasistemas.perfumeiro.repository.filter.PerfumeFilter;
import br.com.lucenasistemas.perfumeiro.service.PerfumeService;
import br.com.lucenasistemas.perfumeiro.service.exception.ImpossivelExcluirEntidadeException;


@Controller
@RequestMapping("perfumes")
public class PerfumesController {
	
	@Autowired
	private FamiliasOlfativas familias;
	
	@Autowired
	private Perfumes perfumes;
	
	@Autowired
	private PerfumeService service;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Perfume perfume) {
		ModelAndView mv = new ModelAndView("perfume/cadastroPerfume");
		mv.addObject("generos", Genero.values());
		mv.addObject("familias", familias.findAll());
		mv.addObject("origens", Origem.values());
		return mv;
	}
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Perfume perfume, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(perfume);
		}
		service.salvar(perfume);
		attributes.addFlashAttribute("mensagem", "Perfume salvo com sucesso!");
		return new ModelAndView("redirect:/perfumes/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(PerfumeFilter perfumeFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("perfume/pesquisaPerfume");
		mv.addObject("generos", Genero.values());
		mv.addObject("familias", familias.findAll());
		mv.addObject("origens", Origem.values());
		
		PageWrapper<Perfume> paginaWrapper = new PageWrapper<>(perfumes.filtrar(perfumeFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PerfumeDTO> pesquisar(String skuOuNome) {
		return perfumes.porSkuOuNome(skuOuNome);
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Perfume perfume) {
		try {
			service.excluir(perfume);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Perfume perfume) {
		ModelAndView mv = novo(perfume);
		mv.addObject(perfume);
		return mv;
	}

}
