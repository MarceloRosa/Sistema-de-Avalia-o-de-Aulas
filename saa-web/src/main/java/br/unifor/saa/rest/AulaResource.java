package br.unifor.saa.rest;

import java.util.List;
/**
 * @author marcelorosa2@hotmail.com
 * @since  05 de janeiro de 2017
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.unifor.saa.business.AulaBO;
import br.unifor.saa.entity.Aula;
import br.unifor.saa.entity.Turma;


@RestController
@RequestMapping("/aula")
public class AulaResource extends SpringBeanAutowiringSupport {

	@Autowired
	private AulaBO aulaBO;

	private List<Aula> listaAulasTurma;

	/*
	exemplo para teste corpo json
	{
	"id" : "30"
	}*/

	@RequestMapping(value = "/listaAulasTurma",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Aula> findAulaByClass(@RequestBody Turma turma) {
		
		Long idTeste = turma.getId();
		listaAulasTurma = aulaBO.listarAulasPorTurma(turma);
		
		return listaAulasTurma;
	}
	
}
