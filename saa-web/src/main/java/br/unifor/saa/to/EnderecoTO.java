package br.unifor.saa.to;

/**
 * @author adrianopatrick@gmail.com
 * @since 17 de dez de 2015
 */
public class EnderecoTO {
	
	private String complemento;
	private String bairro;
	private String cidade;
	private String logradouro;
	
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Override
	public String toString() {
		return "EnderecoTO [complemento=" + complemento + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", logradouro=" + logradouro + "]";
	}

}
