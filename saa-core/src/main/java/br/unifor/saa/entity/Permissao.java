/**
 * 
 */
package br.unifor.saa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.aspectj.internal.lang.annotation.ajcDeclareSoft;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author adrianopatrick@gmail.com
 * @since 7 de dez de 2015
 */
@Entity
@Table(name="permissoes")
public class Permissao implements Serializable {

	private static final long serialVersionUID = 2489782025501425429L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="funcionalidade_id", nullable=false)
	private Funcionalidade funcionalidade;

	@Column(nullable=false)
	private String descricao;
	
	@Column(unique=true, nullable=false)
	private String permissao;
	
	@JsonIgnore
	@ManyToMany(mappedBy="permissoes", fetch=FetchType.LAZY)
	private List<Papel> papeis;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the funcionalidade
	 */
	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	/**
	 * @param funcionalidade the funcionalidade to set
	 */
	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the permissao
	 */
	public String getPermissao() {
		return permissao;
	}

	/**
	 * @param permissao the permissao to set
	 */
	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}
	
	/**
	 * @return the papeis
	 */
	public List<Papel> getPapeis() {
		return papeis;
	}

	/**
	 * @param papeis the papeis to set
	 */
	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((permissao == null) ? 0 : permissao.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permissao other = (Permissao) obj;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		return true;
	}
	
}
