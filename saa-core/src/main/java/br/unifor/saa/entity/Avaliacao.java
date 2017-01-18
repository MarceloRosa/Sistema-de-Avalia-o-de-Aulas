package br.unifor.saa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author marcelorosa2@hotmail.com
 * @since  05 de janeiro de 2017
 */

@Entity
@Table(name="avaliacoes")
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 2852351346804907648L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@JsonIgnore
	@ManyToOne //(cascade = {CascadeType.ALL})
	@JoinColumn(name="aula_id")
	private Aula aula;

	@JsonIgnore
	@ManyToOne //(cascade = {CascadeType.ALL})
	@JoinColumn(name="aluno_id")
	private Usuario aluno;
	
	@Column(nullable=false)
	private Integer pontuacao;
	
	@Column(nullable=false)
	private Boolean anonimo;
	
	@Column
	private String comentario;
	
	@Column(nullable=false, columnDefinition="boolean default false")
	private Boolean moderado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Usuario getAluno() {
		return aluno;
	}

	public void setAluno(Usuario aluno) {
		this.aluno = aluno;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Boolean getModerado() {
		return moderado;
	}

	public void setModerado(Boolean moderado) {
		this.moderado = moderado;
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
		Avaliacao other = (Avaliacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Boolean getAnonimo() {
		return anonimo;
	}

	public void setAnonimo(Boolean anonimo) {
		this.anonimo = anonimo;
	}

}
