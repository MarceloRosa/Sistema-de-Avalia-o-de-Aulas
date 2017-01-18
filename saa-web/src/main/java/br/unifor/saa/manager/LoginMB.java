/**
 * 
 */
package br.unifor.saa.manager;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.unifor.saa.business.UsuarioBO;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.entity.enums.TipoPapel;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.to.SegurancaTO;
import br.unifor.saa.util.Encripta;
import br.unifor.saa.util.MessagesResources;
import br.unifor.saa.utils.MessagesUtils;
import br.unifor.saa.utils.Navigation;

/**
 * @author adrianopatrick@gmail.com
 * @since 4 de dez de 2015
 */
@RequestScoped
@Scope("session")
@Named(value = "loginManager")
@ManagedBean(name = "loginManager")
public class LoginMB {

	@PostConstruct
	public void carregaLocale() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		MessagesResources.setLocale(locale);
	}

	private String email;
	private String senha;
	private String confirmaSenha;

	@Inject
	private UsuarioBO usuarioBO;
	@Inject
	private SegurancaTO segurancaTO;

	public String loggar() {

		try {
			Usuario usuario = usuarioBO.loggar(this.email, this.senha);
			
			segurancaTO.setUsuario(usuario);
			MessagesUtils.info(MessagesResources.getMessages("home.welcome")+" "+usuario.getNome());
			if (!usuario.isPrimeiroAcesso() && usuario.isAtivo()) {
				return redirecionarPorPapel(usuario.maxPapel());
			} else {
				return Navigation.ATUALIZA;
			}
		} catch (BOException e) {
			MessagesUtils.error(e.getMessage());
			return Navigation.FRACASSO;
		} 
	}
	
	public String alteraSenha(){
		
		if(!senha.equals(confirmaSenha)){
			MessagesUtils.error(MessagesResources.getMessages("msg.confirmasenha"));
			return Navigation.FRACASSO;
		}
		
		Usuario usuario = segurancaTO.getUsuario();
		usuario.setSenha(Encripta.encripta(senha));
		usuario.setPrimeiroAcesso(false);
		usuario.setAtivo(true);
		usuarioBO.atualizar(usuario);
		
		return redirecionarPorPapel(usuario.maxPapel());
	}


	public String redirecionarPorPapel(TipoPapel tipoPapel) {

		if (TipoPapel.PROFESSOR.equals(tipoPapel)) {
			return Navigation.HOME_PROFESSOR;
		} else if (TipoPapel.ALUNO.equals(tipoPapel)) {
			return Navigation.HOME_ALUNO;
		} else if(TipoPapel.ADMINISTRADOR.equals(tipoPapel)){
			return Navigation.HOME_ADMINISTRADOR;
		} else {
			return null;
		}
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
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

}
