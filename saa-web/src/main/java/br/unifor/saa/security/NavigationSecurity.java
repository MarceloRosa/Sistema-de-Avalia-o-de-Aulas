package br.unifor.saa.security;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * @author adrianopatrick@gmail.com
 * @since 8 de dez de 2015
 */
@ApplicationScoped
@Named(value = "navigationSecurity")
@ManagedBean(name = "navigationSecurity")
public class NavigationSecurity {

	private Acesso acesso;

	@PostConstruct
	private void carregaAcessos() {
		XStream xstream = new XStream(new StaxDriver());
		xstream.alias("acesso", Acesso.class);
		xstream.alias("recurso", Recurso.class);
		xstream.alias("papel", PapelDeAcesso.class);
		acesso = (Acesso) xstream.fromXML(this.getClass().getClassLoader()
				.getResourceAsStream("navigation_security.xml"));
	}

	public Acesso getAcesso() {
		return acesso;
	}

}
