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

import br.unifor.saa.business.AulaBO;
import br.unifor.saa.business.AvaliacaoBO;
import br.unifor.saa.business.UsuarioBO;
import br.unifor.saa.entity.Aula;
import br.unifor.saa.entity.Avaliacao;
import br.unifor.saa.entity.Turma;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.rest.model.AvaliacaoModel;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoAndroidResource {

	@Autowired
	private AvaliacaoBO avaliacaoBO;

	private Avaliacao avaliacao;

	private List<Avaliacao> listaAvaliacoes;

	@Autowired
	private UsuarioBO usuarioBO;

	private Usuario usuario;

	private Turma turma;

	private String msg;

	private Aula aula;

	@Autowired
	private AulaBO aulaBo;

	/*
	 * 
	 * exemplo para teste corpo json lembrar de selecionar no headers:
	 * Content-Type: application/json
	 * 
	 * { "aulaId" : "7" , "alunoId" : "161" , "pontuacao" : "4" , "comentario" :
	 * "Teste para ver se o serviço rest esta funcionando" }
	 */

	@RequestMapping(value = "/salvaAvaliacoes", method = { RequestMethod.POST,
			RequestMethod.PUT }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String salvarAvaliacao(@RequestBody AvaliacaoModel avaliacaoModel) {

		msg = null;
		avaliacao = new Avaliacao();
		aula = aulaBo.buscarPorId(avaliacaoModel.getAulaId());
		usuario = usuarioBO.buscarPorId(avaliacaoModel.getAlunoId());
		Integer pontuacao = avaliacaoModel.getPontuacao();
		String comentario = avaliacaoModel.getComentario();

		avaliacao.setAula(aula);
		avaliacao.setAluno(usuario);
		avaliacao.setPontuacao(pontuacao);
		avaliacao.setComentario(comentario);
		avaliacao.setModerado(false);
		avaliacao.setAnonimo(false);

		Boolean avaliou = avaliacaoBO.buscarAvaliacaoAulaAluno(aula, usuario);
		if (avaliou) {
			msg = "Erro ao Salvar, Aula já avaliada.";
		} else {
			try {
				avaliacaoBO.salvar(avaliacao);
			} catch (BOException e) {
				e.printStackTrace();
				msg = e.getMessage();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
				msg = "Erro ao salvar! Tente novamente.";
			}
			msg = "Avaliação salva com sucesso!";
		}
		return msg;

	}

	/*
	 * 
	 * exemplo para teste corpo json lembrar de selecionar no headers:
	 * Content-Type: application/json
	 * 
	 * { "id" : "4" }
	 */

	/* Lista todas as avaliações do aluno!!! */
	@RequestMapping(value = "/listaAvaliacoes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Avaliacao> findAvaliByUser(@RequestBody Aula aula) {

		listaAvaliacoes = avaliacaoBO.listaAvaliacoesPorAulaModerado(aula);

		return listaAvaliacoes;
	}
}
