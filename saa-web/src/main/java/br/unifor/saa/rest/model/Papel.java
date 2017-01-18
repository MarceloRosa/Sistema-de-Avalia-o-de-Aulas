/**
 * 
 */
package br.unifor.saa.rest.model;

import java.util.List;

import br.unifor.saa.entity.enums.TipoPapel;

/**
 * @author Marcelo Rosa
 * @since 7 de dez de 2016
 */
public class Papel {

	private Integer id;

	private TipoPapel tipoPapel;

	private List<Permissao> permissoes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoPapel getTipoPapel() {
		return tipoPapel;
	}

	public void setTipoPapel(TipoPapel tipoPapel) {
		this.tipoPapel = tipoPapel;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
}
