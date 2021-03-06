package com.dac.marina.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Artigo implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idArtigo;
    
    private int numOrdem;
    @Column(name="idioma", length=2)
    private String idioma;
    @Column(name="titulo_original", length=256)
    private String titulo_original;
    @Column(name="titulo_ingles", length=256)
    private String titulo_ingles;
    @Column(name="resumo_original", length=2048)
    private String resumo_original;
    @Column(name="resumo_ingles", length=2048)
    private String resumo_ingles;
    @Column(name="palavras_chave_orig", length=256)
    private String palavras_chave_orig;
    @Column(name="palavras_chave_ingles", length=256)
    private String palavras_chave_ingles;
    private int num_paginas;
    
    @ManyToOne
    private Volume volume;
    
    @OneToMany
    private List<Autor> lista_autores;
    

    public Volume getVolume() {
		return volume;
	}

	public void setVolume(Volume volume) {
		this.volume = volume;
	}
    
     public long getIdArtigo() {
		return idArtigo;
	}

	public void setIdArtigo(long idArtigo) {
		this.idArtigo = idArtigo;
	}

    public int getNumOrdem() {
		return numOrdem;
	}

	public void setNumOrdem(int numOrdem) {
		this.numOrdem = numOrdem;
	}

	public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTitulo_original() {
        return titulo_original;
    }

    public void setTitulo_original(String titulo_original) {
        this.titulo_original = titulo_original;
    }

    public String getTitulo_ingles() {
        return titulo_ingles;
    }

    public void setTitulo_ingles(String titulo_ingles) {
        this.titulo_ingles = titulo_ingles;
    }

    public String getResumo_original() {
        return resumo_original;
    }

    public void setResumo_original(String resumo_original) {
        this.resumo_original = resumo_original;
    }

    public String getResumo_ingles() {
        return resumo_ingles;
    }

    public void setResumo_ingles(String resumo_ingles) {
        this.resumo_ingles = resumo_ingles;
    }

    public String getPalavras_chave_orig() {
        return palavras_chave_orig;
    }

    public void setPalavras_chave_orig(String palavras_chave_orig) {
        this.palavras_chave_orig = palavras_chave_orig;
    }

    public String getPalavras_chave_ingles() {
        return palavras_chave_ingles;
    }

    public void setPalavras_chave_ingles(String palavras_chave_ingles) {
        this.palavras_chave_ingles = palavras_chave_ingles;
    }

    public int getNum_paginas() {
        return num_paginas;
    }

    public void setNum_paginas(int num_paginas) {
        this.num_paginas = num_paginas;
    }

    public List<Autor> getLista_autores() {
    	return lista_autores;
   }

   public void setLista_autores(List<Autor> lista_autores) {
	   this.lista_autores = lista_autores;
   }  
    
}
