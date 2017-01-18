package br.unifor.saa.restful;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListaTelefonica {

	private static Listas listas = new Listas();
	
	@CrossOrigin
	@RequestMapping("contatos")
	public ResponseEntity<List<Contatos>> getContatos() {
		return new ResponseEntity<List<Contatos>>(listas.getContatos(), HttpStatus.OK);
	}
}

class Listas{
	
	private List<Contatos> contatos;
	
	public Listas() {
		contatos = new ArrayList<>();
		contatos.add(new Contatos("Ana", "9999-1111", new Operadora("Oi", 1, "Celular")));
		contatos.add(new Contatos("Bernado", "9999-2222", new Operadora("Tim", 2, "Celular")));
		contatos.add(new Contatos("Carlos", "9999-3333", new Operadora("Vivo", 3, "Celular")));
		contatos.add(new Contatos("Daniela", "3122-4444", new Operadora("Embratel", 4, "Fixo")));
		contatos.add(new Contatos("Eduardo", "3133-5555", new Operadora("GVT", 5, "Fixo")));
	}
	
	public List<Contatos> getContatos(){
		return contatos;
	}
	
	public void addContato(Contatos contato){
		contatos.add(contato);
	}
	
	public void removeContato(Contatos contato){
		contatos.remove(contato);
	}
	
}

class Contatos {
	
	private String nome;
	private String telefone;
	private Operadora operadora;
	
	public Contatos(String nome, String telefone, Operadora operadora) {
		this.nome = nome;
		this.telefone = telefone;
		this.operadora = operadora;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Operadora getOperadora() {
		return operadora;
	}
	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}
}

class Operadora {

	private String nome;
	private Integer codigo;
	private String categoria;

	public Operadora(String nome, Integer codigo, String categoria) {
		this.nome = nome;
		this.codigo = codigo;
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
