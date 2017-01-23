package br.unifor.saa.rest.model;

import java.util.List;
/**
 * @author marcelorosa2@hotmail.com
 * @since  05 de janeiro de 2017
 */

public class Turma{

	private Long id;
	
	private String descricao;
	
	private String disciplina;
	
	private String instituicao;
	
	private String semestre;
	
	private Long professorId; 
	
	private List<Integer> alunos;
	
	
	
	
	
	private Boolean ativo;



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



	public String getDisciplina() {
		return disciplina;
	}



	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}



	public Boolean getAtivo() {
		return ativo;
	}



	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}



	public Long getProfessorId() {
		return professorId;
	}



	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}



	public List<Integer> getAlunos() {
		return alunos;
	}



	public void setAlunos(List<Integer> alunos) {
		this.alunos = alunos;
	}
	



}
