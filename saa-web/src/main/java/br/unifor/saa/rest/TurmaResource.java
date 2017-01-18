package br.unifor.saa.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.unifor.saa.business.TurmaBO;
import br.unifor.saa.business.UsuarioBO;
import br.unifor.saa.entity.Turma;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.rest.model.*;
/**
 * @author marcelorosa2@hotmail.com
 * @since  05 de janeiro de 2017
 */
//@Component
//@Path("/turma")


@RestController
@RequestMapping("/turma")
public class TurmaResource extends SpringBeanAutowiringSupport {

	@Autowired
	private TurmaBO turmaBO;

	private List<Turma> listaTurmas;

	/*
	exemplo para teste corpo json
	{
	"id" : "21"
	}*/

	@RequestMapping(value = "/listaTurma",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Turma> findTurmaByUser(@RequestBody Usuario usuario) {
		
		listaTurmas = turmaBO.listaTurmaPorAluno(usuario);
		
		return listaTurmas;
	}
	
}
