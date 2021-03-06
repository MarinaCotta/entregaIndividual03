package com.dac.marina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dac.marina.model.Artigo;
import com.dac.marina.model.Autor;
import com.dac.marina.repository.ArtigoRepository;
import com.dac.marina.repository.AutorRepository;

@Controller
//@RequestMapping("/api/v1")
public class ArtigoController {
	
	@Autowired
	private ArtigoRepository artigoRepository;
	
	@Autowired
	private AutorRepository autorRepository;

	
	@RequestMapping(value="/cadastrarArtigo", method=RequestMethod.GET)
	public String form() {
		return "artigo/cadastroArtigo";
	}
	
	@RequestMapping(value="/cadastrarArtigo", method=RequestMethod.POST)
	public String form(@Validated Artigo artigo, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarArtigo";
		}
		
		artigoRepository.save(artigo);
		attributes.addFlashAttribute("mensagem", "Artigo cadastrado com sucesso!");
		return "redirect:/cadastrarArtigo";
	}
	
	@RequestMapping(value="/detalheArtigo/{idArtigo}", method=RequestMethod.GET)
	public ModelAndView detalhesArtigo(@PathVariable("idArtigo") long idArtigo){
		Artigo artigo = artigoRepository.findByIdArtigo(idArtigo);
		ModelAndView mv = new ModelAndView("artigo/detalhesArtigo");
		mv.addObject("artigo", artigo);
		
		List<Autor> autores = autorRepository.findByArtigo(artigo, Sort.by("numOrdem").ascending());
		mv.addObject("autores", autores);
		
		return mv;
	}
	
	@RequestMapping(value="/detalheArtigo/{idArtigo}", method=RequestMethod.POST)
	public String autorArtigoPost(@PathVariable("idArtigo") long idArtigo, @Validated Autor autor, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/detalheArtigo/{idArtigo}";
		}
		Artigo artigo = artigoRepository.findByIdArtigo(idArtigo);
		autor.setArtigo(artigo);
		autorRepository.save(autor);
		attributes.addFlashAttribute("mensagem", "Artigo adicionado com sucesso!");
		return "redirect:/detalheArtigo/{idArtigo}";
	}
	
	@GetMapping("/editarArtigo/{idArtigo}")
	public ModelAndView editarVolume(@PathVariable("idArtigo") long idArtigo) {
		Artigo artigo = artigoRepository.findByIdArtigo(idArtigo);
		ModelAndView mv = new ModelAndView("artigo/formEditarArtigo");
		mv.addObject("artigoObj", artigo);
		List<Autor> autores = autorRepository.findByArtigo(artigo, Sort.by("numOrdem").ascending());
		mv.addObject("autores", autores);
		return mv;
	}
	
	@PostMapping("/atualizarArtigo")
	public ModelAndView formEditarVolume(long idArtigo, @Validated Artigo artigo, BindingResult result, RedirectAttributes attributes) {
		artigo.setIdArtigo(idArtigo);
		artigoRepository.save(artigo);
		attributes.addFlashAttribute("mensagem", "Artigo editado com sucesso!");
		ModelAndView mv = new ModelAndView("artigo/detalhesArtigo");
		mv.addObject("artigo", artigo);
		List<Autor> autores = autorRepository.findByArtigo(artigo, Sort.by("numOrdem").ascending());
		mv.addObject("autores", autores);
		return mv;
	}
	
	@GetMapping("/artigos")
	public List<Artigo> listaTodos() {
		return artigoRepository.findAll();
	}
	
	@GetMapping("/artigos/{id}")
	public ResponseEntity<Artigo> getArtigoById(@PathVariable(value = "id") Long artigoId)
	 throws ResourceNotFoundException {
		Artigo artigo = artigoRepository.findById(artigoId)
	      .orElseThrow(() -> new ResourceNotFoundException());
	    return ResponseEntity.ok().body(artigo);
	}
	
	@GetMapping("/autoresArtigo/{id}")
	public ResponseEntity<List<Autor>> getArtigosVolume(@PathVariable(value = "id") Long artigoId)
	 throws ResourceNotFoundException {
		Artigo artigo = artigoRepository.findById(artigoId)
	      .orElseThrow(() -> new ResourceNotFoundException());
	    return ResponseEntity.ok().body(artigo.getLista_autores());
	}
	
	
}
