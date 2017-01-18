package br.unifor.saa.rest.model;

/**
 * @author Marcelo Rosa
 * @since 7 de dez de 2016
 */
public class AvaliacaoModel {

	
	private Long aulaId;
	
	private Long alunoId;
	
	private Integer pontuacao;
	
	private String comentario;
	

	public Long getAulaId() {
		return aulaId;
	}

	public void setAulaId(Long aulaId) {
		this.aulaId = aulaId;
	}

	public Long getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(Long alunoId) {
		this.alunoId = alunoId;
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

}
