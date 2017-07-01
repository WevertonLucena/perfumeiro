package br.com.lucenasistemas.perfumeiro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.lucenasistemas.perfumeiro.controller.page.PageWrapper;
import br.com.lucenasistemas.perfumeiro.model.FamiliaOlfativa;
import br.com.lucenasistemas.perfumeiro.repository.FamiliasOlfativas;
import br.com.lucenasistemas.perfumeiro.repository.filter.FamiliaOlfativaFilter;
import br.com.lucenasistemas.perfumeiro.service.FamiliaOlfativaService;
import br.com.lucenasistemas.perfumeiro.service.exception.ImpossivelExcluirEntidadeException;



@Controller
@RequestMapping("familias")
public class FamiliasOlfativasController {
	
	@Autowired
	private FamiliasOlfativas familias;
	
	@Autowired
	private FamiliaOlfativaService service;
	
	@RequestMapping("/nova")
	public ModelAndView nova(FamiliaOlfativa familiaOlfativa) {
		ModelAndView mv =  new ModelAndView("familia/cadastroFamilia");
		return mv;
	}
	
	@RequestMapping(value = {"/nova","{\\d+}"}, method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid FamiliaOlfativa familia, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()) {
			return nova(familia);
		}
		service.salvar(familia);
		
		attributes.addFlashAttribute("mensagem", "Família olfativa salva com sucesso");
		return new ModelAndView("redirect:/familias/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(FamiliaOlfativaFilter familiaOlfativaFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("familia/pesquisaFamilias");
		
		PageWrapper<FamiliaOlfativa> paginaWrapper = new PageWrapper<>(familias.filtrar(familiaOlfativaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") FamiliaOlfativa familia) {
		try {
			service.excluir(familia);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") FamiliaOlfativa familia) {
		ModelAndView mv = nova(familia);
		mv.addObject(familia);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid FamiliaOlfativa familia, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		familia = service.salvar(familia);
		return ResponseEntity.ok(familia);
	}

}
