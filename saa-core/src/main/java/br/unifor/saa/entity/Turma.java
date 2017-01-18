package br.unifor.saa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * @author marcelorosa2@hotmail.com
 * @since  05 de janeiro de 2017
 */
@Entity
@Table(name="turmas")
public class Turma implements Serializable{

	private static final long serialVersionUID = -512021213677005338L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable=false)
	private String descricao;
	
	@Column(nullable=false)
	private String instituicao;
	
	@Column(nullable=false)
	private String semestre;
	
	@Column(nullable=false)
	private String disciplina;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private byte[] foto;
	
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name="professor_id", nullable=false)
	private Usuario professor;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="alocacoes", joinColumns = @JoinColumn(name="turma_id" ,referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(name="aluno_id", referencedColumnName="id"))
	private List<Usuario> alunos;
	
	@Column(nullable=false)
	private Boolean ativo;
	
	@Column(nullable=false)
	private Boolean moderar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public List<Usuario> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Usuario> alunos) {
		this.alunos = alunos;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getModerar() {
		return moderar;
	}

	public void setModerar(Boolean moderar) {
		this.moderar = moderar;
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
		Turma other = (Turma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
