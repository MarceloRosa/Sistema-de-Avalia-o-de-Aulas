package br.unifor.saa.manager;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.unifor.saa.business.UsuarioBO;
import br.unifor.saa.entity.Papel;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.entity.enums.TipoPapel;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.utils.MessagesUtils;
import br.unifor.saa.utils.Navigation;

@RequestScoped
@Scope("session")
@Named(value = "cadUsuarioManager")
@ManagedBean(name = "cadUsuarioManager")
public class cadUsuarioMB {
	
	private String nome;
	private String email;
	private TipoPapel tipoPapel;
	
	@Inject
	private UsuarioBO usuarioBO;
	
	public String preparaCadastro(){
		return Navigation.CADASTRO;
	}
	
	public String voltar(){
		return Navigation.VOLTAR;
	}
	
	public String salvar(){
		
		Usuario usuario = new Usuario();
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		usuario.setPapeis(new ArrayList<>());
		Papel papel = usuarioBO.buscaPapelPorTipo(this.tipoPapel);
		usuario.getPapeis().add(papel);
		
		try {
			this.usuarioBO.salvar(usuario);
		} catch (BOException e) {
			MessagesUtils.error(e.getMessage());
			return Navigation.FRACASSO;
		} 
		
		return Navigation.SUCESSO;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TipoPapel getTipoPapel() {
		return tipoPapel;
	}
	public void setTipoPapel(TipoPapel tipoPapel) {
		this.tipoPapel = tipoPapel;
	}
	

}
