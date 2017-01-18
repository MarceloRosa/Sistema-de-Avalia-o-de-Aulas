package br.unifor.saa.rest.model;

import java.util.Date;
/**
 * @author Marcelo Rosa
 * @since 7 de dez de 2016
 */
public class Aula{

	private Long id;
	
	private Turma turma;
	
	private String descricao;
	
	private Date dataAula;

	private Double media;
	
	private Long sequencia;
	
	private Boolean ativo;
	
	private byte[] material;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataAula() {
		return dataAula;
	}

	public void setDataAula(Date dataAula) {
		this.dataAula = dataAula;
	}

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

	public Long getSequencia() {
		return sequencia;
	}

	public void setSequencia(Long sequencia) {
		this.sequencia = sequencia;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public byte[] getMaterial() {
		return material;
	}

	public void setMaterial(byte[] material) {
		this.material = material;
	}
	
}
