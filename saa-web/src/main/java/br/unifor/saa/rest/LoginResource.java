package br.unifor.saa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.unifor.saa.business.UsuarioBO;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.exceptions.BOException;

/**
 * @author marcelorosa2@hotmail.com
 * @since  05 de janeiro de 2017
 */

@RestController
@RequestMapping("/login")
public class LoginResource {

	@Autowired
	private UsuarioBO usuarioBO;

	private Usuario user;

	/*
	 * 
	 * exemplo para teste corpo json
	 * lembrar de selecionar no headers: Content-Type: application/json
	 * 
	{
	"email" : "marcelo@aluno.com" ,
	 "senha" : "123"
	}*/
	@RequestMapping(value = "/json", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Usuario loggar(@RequestBody Usuario usuario) {

		if (usuario.getEmail() != null && usuario.getSenha() != null) {
			Usuario user;
			try {
				user = usuarioBO.loggar(usuario.getEmail(), usuario.getSenha());
				if (user != null) {
					return user;
				} else {
					return null;
				}
			} catch (BOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}
}
