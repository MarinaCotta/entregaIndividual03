package com.dac.marina.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
@Table(name="volume")

@Entity
public class Volume implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVolume;
    @Column(name="siglaEvento", length=32)
    private String siglaEvento;
    private int numEdicao;
    @Column(name="cidade", length=64)
    private String cidade;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private LocalDate dataInicio;
    @Column(name="descrPort", length=2048)
    private String descrPort;
    @Column(name="descrIngles", length=2048)
	private String descrIngles;
    
    @OneToMany//(cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Artigo> lista_artigos;

    public Long getIdVolume() {
        return idVolume;
    }

    public void setId(long idVolume) {
        this.idVolume = idVolume;
    }
    
    
    public String getSiglaEvento() {
		return siglaEvento;
	}

	public void setSiglaEvento(String siglaEvento) {
		this.siglaEvento = siglaEvento;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getNumEdicao() {
		return numEdicao;
	}

	public void setNumEdicao(int numEdicao) {
		this.numEdicao = numEdicao;
	}

	public String getDescrPort() {
		return descrPort;
	}

	public void setDescrPort(String descrPort) {
		this.descrPort = descrPort;
	}

	public String getDescrIngles() {
		return descrIngles;
	}

	public void setDescrIngles(String descrIngles) {
		this.descrIngles = descrIngles;
	}

	public List<Artigo> getLista_artigos() {
        return lista_artigos;
    }

    public void setLista_artigos(List<Artigo> lista_artigos) {
        this.lista_artigos = lista_artigos;
    }

    
}
