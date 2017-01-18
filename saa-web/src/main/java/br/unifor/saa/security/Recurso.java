/**
 * 
 */
package br.unifor.saa.security;

import java.util.List;

/**
 * @author adrianopatrick@gmail.com
 * @since 10 de dez de 2015
 */
public class Recurso{
	
	private String url;
	
	private List<PapelDeAcesso> papeis;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<PapelDeAcesso> getPapeis() {
		return papeis;
	}
	public void setPapeis(List<PapelDeAcesso> papeis) {
		this.papeis = papeis;
	}
}
