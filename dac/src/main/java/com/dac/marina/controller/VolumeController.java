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
import com.dac.marina.model.Volume;
import com.dac.marina.repository.ArtigoRepository;
import com.dac.marina.repository.VolumeRepository;


@Controller
//@RequestMapping("/api/v1")
public class VolumeController {
	
	@RequestMapping(value="/cadastrarVolume", method=RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("volume/cadastroVolume");
		mv.addObject("volumeObj", new Volume());
		return mv;
	}
	
	@Autowired
	private VolumeRepository volumeRepository;
	
	@Autowired
	private ArtigoRepository artigoRepository;
	
	@RequestMapping(value="/cadastrarVolume", method=RequestMethod.POST)
	public String form(@Validated Volume volume, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarVolume";
		}
		
		volumeRepository.save(volume);
		attributes.addFlashAttribute("mensagem", "Volume cadastrado com sucesso!");
		return "redirect:/";
	}
	
	@RequestMapping("/")
	public ModelAndView listaVolumes() {
		ModelAndView mv = new ModelAndView("index");
		List<Volume> volumes = volumeRepository.findAll(Sort.by("siglaEvento").descending().and(Sort.by("dataInicio")));
		mv.addObject("volumes", volumes);
		return mv;
	}
	
	@RequestMapping(value="/{idVolume}", method=RequestMethod.GET)
	public ModelAndView detalhesVolume(@PathVariable("idVolume") long idVolume){
		Volume volume = volumeRepository.findByIdVolume(idVolume);
		ModelAndView mv = new ModelAndView("volume/detalhesVolume");
		mv.addObject("volume", volume);
		List<Artigo> artigos = artigoRepository.findByVolume(volume, Sort.by("numOrdem").ascending());
		mv.addObject("artigos", artigos);
		
		return mv;
	}
	
	@RequestMapping(value="/{idVolume}", method=RequestMethod.POST)
	public String artigoVolumePost(@PathVariable("idVolume") long idVolume, @Validated Artigo artigo,  BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{idVolume}";
		}
		Volume volume = volumeRepository.findByIdVolume(idVolume);
		artigo.setVolume(volume);
		artigoRepository.save(artigo);
		attributes.addFlashAttribute("mensagem", "Artigo adicionado com sucesso!");
		return "redirect:/{idVolume}";
	}
	
	@RequestMapping("/removerVolume")
	public String removerVolume(long idVolume) {
		Volume volume = volumeRepository.findByIdVolume(idVolume);
		volumeRepository.delete(volume);
		return "redirect:/";
	}
	
	@RequestMapping("/deletarArtigo")
	public String removerArtigo(long idArtigo) {
		Artigo artigo = artigoRepository.findByIdArtigo(idArtigo);
		artigoRepository.delete(artigo);
		
		Volume volume = artigo.getVolume();
		long codigoLong = volume.getIdVolume();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
	
	@GetMapping("/editarVolume/{idVolume}")
	public ModelAndView editarVolume(@PathVariable("idVolume") long idVolume) {
		Volume volume = volumeRepository.findByIdVolume(idVolume);
		ModelAndView mv = new ModelAndView("volume/formEditarVolume");
		mv.addObject("volumeObj", volume);
		List<Artigo> artigos = artigoRepository.findByVolume(volume, Sort.by("numOrdem").ascending());
		mv.addObject("artigos", artigos);
		return mv;
	}
	
	@PostMapping("/atualizarVolume")
	public ModelAndView formEditarVolume(long idVolume, @Validated Volume volume, BindingResult result, RedirectAttributes attributes) {
		volume.setId(idVolume);
		volumeRepository.save(volume);
		attributes.addFlashAttribute("mensagem", "Volume editado com sucesso!");
		ModelAndView mv = new ModelAndView("volume/detalhesVolume");
		mv.addObject("volume", volume);
		
		List<Artigo> artigos = artigoRepository.findByVolume(volume, Sort.by("numOrdem").ascending());
		mv.addObject("artigos", artigos);
		return mv;
	}
	
	
	@GetMapping("/volume/{id}/artigos")
	public ResponseEntity<List<Artigo>> getArtigosVolume(@PathVariable(value = "id") Long volumeId)
	 throws ResourceNotFoundException {
		Volume volume = volumeRepository.findById(volumeId)
	      .orElseThrow(() -> new ResourceNotFoundException());
	    return ResponseEntity.ok().body(volume.getLista_artigos());
	}

	@GetMapping("/volume/{id}/artigos/autores")
	public ResponseEntity<List<Autor>> getAutoresArtigoVolume(@PathVariable(value = "id") Long volumeId, Long artigoId)
	 throws ResourceNotFoundException {
		Volume volume = volumeRepository.findById(volumeId)
	      .orElseThrow(() -> new ResourceNotFoundException());
		List<Artigo> auxListArt = volume.getLista_artigos();
		List<Autor> auxListAutor = null;
		for (int i = 0; i< auxListArt.size(); i++) {
			auxListAutor = auxListArt.get(i).getLista_autores();
		}
	    return ResponseEntity.ok().body(auxListAutor);
	}
	
}
