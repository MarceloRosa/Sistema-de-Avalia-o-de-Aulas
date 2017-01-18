package br.unifor.saa.rest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AvaliacaoType", propOrder = {
    "comentario",
    "aluno"
})
public class AvaliacaoType extends EntityType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String comentario;
    private String aluno;
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getAluno() {
		return aluno;
	}
	public void setAluno(String aluno) {
		this.aluno = aluno;
	}
}
