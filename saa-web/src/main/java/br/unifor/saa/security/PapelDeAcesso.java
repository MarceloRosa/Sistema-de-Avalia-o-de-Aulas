/**
 * 
 */
package br.unifor.saa.security;

import br.unifor.saa.entity.enums.TipoPapel;

/**
 * @author adrianopatrick@gmail.com
 * @since 10 de dez de 2015
 */
public class PapelDeAcesso {
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public TipoPapel getTipoPapel(){
		return TipoPapel.buscarPorDescricao(this.getNome());
	}
	
}
