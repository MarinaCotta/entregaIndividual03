package com.dac.marina.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dac.marina.model.Artigo;
import com.dac.marina.model.Autor;
import com.dac.marina.repository.AutorRepository;

@Controller
//@RequestMapping("/api/v1")
public class AutorController {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@RequestMapping(value="/cadastrarAutor", method=RequestMethod.GET)
	public String form() {
		return "autor/cadastroAutor";
	}
	
	@RequestMapping(value="/cadastrarAutor", method=RequestMethod.POST)
	public String form(@Validated Autor autor, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarAutor";
		}
		
		autorRepository.save(autor);
		attributes.addFlashAttribute("mensagem", "Autor cadastrado com sucesso!");
		return "redirect:/cadastrarAutor";
	}
	
	@RequestMapping(value="/detalheAutor/{idAutor}", method=RequestMethod.GET)
	public ModelAndView detalhesAutor(@PathVariable("idAutor") long idAutor){
		Autor autor = autorRepository.findByIdAutor(idAutor);
		ModelAndView mv = new ModelAndView("autor/detalhesAutor");
		mv.addObject("autor", autor);
		return mv;
	}
	
	@RequestMapping("/removerAutor")
	public String removerAutor(long idAutor) {
		Autor autor = autorRepository.findByIdAutor(idAutor);
		autorRepository.delete(autor);
		
		Artigo artigo = autor.getArtigo();
		long codigoLong = artigo.getIdArtigo();
		String codigo = "" + codigoLong;
		return "redirect:/detalheArtigo/" + codigo;
	}
	
	@GetMapping("/editarAutor/{idAutor}")
	public ModelAndView editarAutor(@PathVariable("idAutor") long idAutor) {
		Autor autor = autorRepository.findByIdAutor(idAutor);
		ModelAndView mv = new ModelAndView("autor/formEditarAutor");
		mv.addObject("autorObj", autor);
		return mv;
	}
	
	@PostMapping("/atualizarAutor")
	public String formEditarAutor(long idAutor, @Validated Autor autor, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarAutor";
		}
		autor.setIdAutor(idAutor);
		autorRepository.save(autor);
		attributes.addFlashAttribute("mensagem", "Autor editado com sucesso!");
		return "autor/detalhesAutor";
	}
	
	@GetMapping("/autores")
	public List<Autor> listaTodos() {
		return autorRepository.findAll();
	}
	
	@GetMapping("/autores/{id}")
	public ResponseEntity<Autor> getAutorById(@PathVariable(value = "id") Long autorId)
	 throws ResourceNotFoundException {
		Autor autor = autorRepository.findById(autorId)
	      .orElseThrow(() -> new ResourceNotFoundException());
	    return ResponseEntity.ok().body(autor);
	}
	
	@PutMapping("/autores/{id}")
	public ResponseEntity<Autor> atualizaAutor(@PathVariable(value = "id") Long autorId,
	  @Validated @RequestBody Autor AutorItem) throws ResourceNotFoundException {
	    Autor autor = autorRepository.findById(autorId)
	    .orElseThrow(() -> new ResourceNotFoundException());

	    autor.setNumOrdem(AutorItem.getNumOrdem());
	    autor.setEmail(AutorItem.getEmail());
	    autor.setPrimeiro_nome(AutorItem.getPrimeiro_nome());
	    autor.setNome_meio(AutorItem.getNome_meio());
	    autor.setSobrenome(AutorItem.getSobrenome());
	    autor.setAfiliacao(AutorItem.getAfiliacao());
	    autor.setAfiliacao_ingles(AutorItem.getAfiliacao_ingles());
	    autor.setPais(AutorItem.getPais());
	    autor.setOrcID(AutorItem.getOrcID());

	    final Autor autorAtualizado = autorRepository.save(autor);
	    return ResponseEntity.ok(autorAtualizado);
	}
	
	@DeleteMapping("/autores/{id}")
	public Map<String, Boolean> deleteAutor(@PathVariable(value = "id") Long autorId)
	  throws ResourceNotFoundException {
	    Autor autor = autorRepository.findById(autorId)
	      .orElseThrow(() -> new ResourceNotFoundException());

	    autorRepository.delete(autor);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("Autor excluido com sucesso", Boolean.TRUE);
	    return response;
	}

}
