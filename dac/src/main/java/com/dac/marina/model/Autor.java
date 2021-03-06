package com.dac.marina.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Autor implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAutor;

    private int numOrdem;
    private String email;
    @Column(name="primeiro_nome", length=64)
    private String primeiro_nome;
    @Column(name="nome_meio", length=64)
    private String nome_meio;
    @Column(name="sobrenome", length=64)
    private String sobrenome;
    @Column(name="afiliacao", length=256)
    private String afiliacao;
    @Column(name="afiliacao_ingles", length=256)
    private String afiliacao_ingles;
    @Column(name="pais", length=2)
    private String pais;
    private String OrcID;

    @ManyToOne
	private Artigo artigo;
    
    public long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(long idAutor) {
        this.idAutor = idAutor;
    }

    public int getNumOrdem() {
		return numOrdem;
	}

	public void setNumOrdem(int numOrdem) {
		this.numOrdem = numOrdem;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimeiro_nome() {
        return primeiro_nome;
    }

    public void setPrimeiro_nome(String primeiro_nome) {
        this.primeiro_nome = primeiro_nome;
    }

    public String getNome_meio() {
        return nome_meio;
    }

    public void setNome_meio(String nome_meio) {
        this.nome_meio = nome_meio;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getAfiliacao() {
        return afiliacao;
    }

    public void setAfiliacao(String afiliacao) {
        this.afiliacao = afiliacao;
    }

    public String getAfiliacao_ingles() {
        return afiliacao_ingles;
    }

    public void setAfiliacao_ingles(String afiliacao_ingles) {
        this.afiliacao_ingles = afiliacao_ingles;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getOrcID() {
        return OrcID;
    }

    public void setOrcID(String OrcID) {
        this.OrcID = OrcID;
    }

	public Artigo getArtigo() {
		return artigo;
	}

	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}
    
    
}

