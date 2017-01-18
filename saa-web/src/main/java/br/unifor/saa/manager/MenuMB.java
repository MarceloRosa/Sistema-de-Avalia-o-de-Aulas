/**
 * 
 */
package br.unifor.saa.manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.LoginException;

import org.springframework.context.annotation.Scope;

import br.unifor.saa.to.SegurancaTO;
import br.unifor.saa.utils.Navigation;

/**
 * @author adrianopatrick@gmail.com
 * @since 2 de dez de 2015
 */
@Named
@ManagedBean
@RequestScoped
@Scope("session")
public class MenuMB {

	private Integer option;
	@Inject
	private SegurancaTO segurancaTO;

	public String sair() {
		try {
			segurancaTO.logout();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		return Navigation.SAIR;
	}

	public Integer getOption() {
		return option;
	}

	public void setOption(Integer option) {
		this.option = option;
	}

}
