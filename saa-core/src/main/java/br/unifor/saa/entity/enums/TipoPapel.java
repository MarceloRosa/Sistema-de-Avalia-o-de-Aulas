/**
 * 
 */
package br.unifor.saa.entity.enums;

/**
 * @author adrianopatrick@gmail.com
 * @since 14 de dez de 2015
 */
public enum TipoPapel {
	
	ALUNO("Aluno"), PROFESSOR("Professor"), ADMINISTRADOR("Administrador");
	
	private TipoPapel(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public static TipoPapel buscarPorDescricao(String descricao){
		for (TipoPapel tipo : values()) {
			if(tipo.getDescricao().equalsIgnoreCase(descricao)){
				return tipo;
			}
		}
		return null;
	}
	
	public static TipoPapel buscarPorOrdinal(int ordinal){
		for (TipoPapel tipo : values()) {
			if(tipo.ordinal() == ordinal){
				return tipo;
			}
		}
		return null;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
