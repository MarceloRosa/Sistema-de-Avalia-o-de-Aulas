package br.unifor.saa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * @author marcelorosa2@hotmail.com
 * @since  05 de janeiro de 2017
 */
@Entity
@Table(name="aulas")
public class Aula implements Serializable{

	private static final long serialVersionUID = 5595223204403201823L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="turma_id")
	private Turma turma;
	
	@Column(nullable=false)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_aula", nullable=false)
	private Date dataAula;
	
	@Transient
	private Double media;
	
	@Transient
	private Long sequencia;
	
	@Column
	private Boolean ativo;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
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

	public byte[] getMaterial() {
		return material;
	}

	public void setMaterial(byte[] material) {
		this.material = material;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
