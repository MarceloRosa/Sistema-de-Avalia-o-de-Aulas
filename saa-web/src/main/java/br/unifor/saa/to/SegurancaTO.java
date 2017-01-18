/**
 * 
 */
package br.unifor.saa.to;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.unifor.saa.entity.Usuario;
import br.unifor.saa.utils.Navigation;

/**
 * @author patrick.cunha
 * @since 07/05/2015
 */
@SessionScoped
@Scope("session")
@ManagedBean(name = "segurancaTO")
@Controller(value = "segurancaTO")
public class SegurancaTO implements Serializable {

	private static final long serialVersionUID = -9069250861713212366L;
	private Logger logger = Logger.getLogger(SegurancaTO.class);
	private Usuario usuario;

	public boolean isAutenticado() {
		return usuario != null;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Long getUsuarioID(){
		return usuario.getId();
	}
	
	public String logout() throws LoginException {
		logger.info("logout do sistema");
		this.getRequest().getSession(false).invalidate();

		return Navigation.SAIR;
	}
	
	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

}
