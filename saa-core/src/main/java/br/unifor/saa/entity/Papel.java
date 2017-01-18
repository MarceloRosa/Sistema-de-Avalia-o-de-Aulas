/**
 * 
 */
package br.unifor.saa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.unifor.saa.entity.enums.TipoPapel;

/**
 * @author adrianopatrick@gmail.com
 * @since 7 de dez de 2015
 */
@Entity
@Table(name="papeis")
public class Papel implements Serializable {

	private static final long serialVersionUID = -6442104966262202162L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.ORDINAL)
	private TipoPapel tipoPapel;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "configuracoes", 
		joinColumns = @JoinColumn(name = "papel_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "permissao_id", referencedColumnName = "id"))
	private List<Permissao> permissoes;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public TipoPapel getTipoPapel() {
		return tipoPapel;
	}

	public void setTipoPapel(TipoPapel tipoPapel) {
		this.tipoPapel = tipoPapel;
	}

	/**
	 * @return the permissoes
	 */
	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	/**
	 * @param permissoes the permissoes to set
	 */
	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
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
		Papel other = (Papel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Papel [id=" + id + ", tipoUsuario=" + tipoPapel
				+ ", permissoes=" + permissoes + "]";
	}
	
}
