package br.unifor.saa.rest;

import java.util.ArrayList;
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
import br.unifor.saa.rest.model.RetornoModel;

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
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	private Turma turma;
	
	private List<Usuario> alunos;

	private List<Turma> listaTurmas;

	/*Listar turmas por aluno
	
	exemplo para teste corpo json
	
	{
	"id" : "21"
	}*/

	@RequestMapping(value = "/listaTurma",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Turma> findTurmaByUser(@RequestBody Usuario usuario) {
		
		listaTurmas = turmaBO.listaTurmaPorAluno(usuario);
		
		return listaTurmas;
	}
	
	/*Listar turmas por professor
	 * 
	exemplo para teste corpo json

	{
	"id" : "20"
	}*/

	@RequestMapping(value = "/listaTurmaProf",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Turma> findTurmaByTeacher(@RequestBody Usuario professor) {
		
		listaTurmas = turmaBO.listaTurmaPorProfessor(professor);
		
		return listaTurmas;
	}
	
	/*Salvar nova turma
	 * 
	 * exemplo para teste corpo json lembrar de selecionar no headers:
	 * Content-Type: application/json
	 * 
	 * { "descricao" : "Descrição da turma para teste de nova turma" , "disciplina" : "Este é o nome da disciplina" , "instituicao" : "UNIFOR" , "semestre" :  "4 semestre" , "alunos" : [ "21", "22"] , "ativo" : "true" , "moderar" : "true" , "professor" : "20"}
	 */

	@RequestMapping(value = "/salvaTurma", method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody RetornoModel cadastrarTurma(@RequestBody br.unifor.saa.rest.model.Turma turmaModel) {
        
		RetornoModel retorno = new RetornoModel();
        retorno.setRetorno(null);
        
        List <Integer> lista = new ArrayList<>();
        lista = turmaModel.getAlunos();
        alunos = new ArrayList<Usuario>();
        for (Integer usuario2 : lista) {
        	alunos.add(usuarioBO.buscarPorId(usuario2.longValue()));
			
		}
        
		turma = new Turma();
		
		turma.setDescricao(turmaModel.getDescricao());
		turma.setDisciplina(turmaModel.getDisciplina());
		turma.setInstituicao(turmaModel.getInstituicao());
		turma.setSemestre(turmaModel.getSemestre());
		turma.setAlunos(alunos);
		turma.setAtivo(turmaModel.getAtivo());
		turma.setModerar(true);
		turma.setProfessor(usuarioBO.buscarPorId(turmaModel.getProfessorId()));

		try{
			turmaBO.salvar(turma);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			retorno.setRetorno("Erro ao salvar Turma! Tente novamente.");
		}
		retorno.setRetorno("Nova Turma salva com sucesso!");
		
		return retorno;

	}

	
}
