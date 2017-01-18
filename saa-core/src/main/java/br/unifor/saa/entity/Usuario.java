/**
 * 
 */
package br.unifor.saa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.unifor.saa.entity.enums.TipoPapel;

/**
 * @author adrianopatrick@gmail.com
 * @since 1 de dez de 2015
 */
/**
 * @author marcelorosa2@hotmail.com
 * @since  05 de janeiro de 2017
 */

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(unique = true, nullable = false)
	@Email(message = "email informado está fora do padrão")
	private String email;

	@Column(nullable = false)
	private String senha;

	@Column(name = "primeiro_acesso", nullable = false)
	private boolean primeiroAcesso;

	@Column(nullable = false)
	private boolean ativo;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "perfis", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "papel_id", referencedColumnName = "id") )
	private List<Papel> papeis;

	/**
	 * Entendendo que o enum foi criado em ordem de visibilidade
	 */
	public TipoPapel maxPapel() {
		int maior = 0;
		for (Papel papel : papeis) {
			if (papel.getTipoPapel().ordinal() > maior) {
				maior = papel.getTipoPapel().ordinal();
			}
		}
		return TipoPapel.buscarPorOrdinal(maior);
	}
	
	public TipoPapel getMaxPapel(){
		return maxPapel();
	}

	@Transient
	public String getNomeEmail() {
		String nomeEmail = null;
		if (this.email != null) {
			int pos = this.email.indexOf("@");
			nomeEmail = this.email.substring(0, pos);
		} 
		return nomeEmail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isPrimeiroAcesso() {
		return primeiroAcesso;
	}

	public void setPrimeiroAcesso(boolean primeiroAcesso) {
		this.primeiroAcesso = primeiroAcesso;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", email=" + email + ", papeis=" + papeis
				+ "]";
	}
}
