/**
 * 
 */
package br.unifor.saa.business;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.saa.aspectj.PermitAll;
import br.unifor.saa.aspectj.RolesAllowed;
import br.unifor.saa.dao.AvaliacaoDAO;
import br.unifor.saa.entity.Aula;
import br.unifor.saa.entity.Avaliacao;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.util.MessagesResources;

/**
 *  Bussiness Object responsável por inserir, salvar, atualizar e listar avaliações
 * @author adrianopatrick@gmail.com
 * @since 16 de mar de 2016
 */

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AvaliacaoBO {

	@Inject
	private AvaliacaoDAO avaliacaoDAO;

	public void atualizar(Avaliacao avaliacao) throws BOException {
		try {
			this.avaliacaoDAO.atualizar(avaliacao);
		} catch (Exception e) {
			throw new BOException(
					MessagesResources
							.getMessages("moderarAula.msg_publicar_erro"));
		}
	}

	@PermitAll
	public List<Avaliacao> listaAvaliacaoPorAluno(Usuario aluno) {
		return avaliacaoDAO.listaAvaliacaoPorAluno(aluno);
	}

	@PermitAll
	public void salvar(Avaliacao avaliacao) throws BOException {
		try {
			if (this.avaliacaoDAO.buscarAvaliacaoAulaAluno(avaliacao.getAula(),
					avaliacao.getAluno())) {
				throw new BOException(MessagesResources
						.getMessages("cadAvaliacao.msg_ja_salva"));
			}
			this.avaliacaoDAO.salvar(avaliacao);
		} catch (Exception e) {
			throw new BOException(
					MessagesResources
							.getMessages("cadAvaliacao.msg_salvar_error"));
		}
	}

	@RolesAllowed("AULA_INSERIR")
	public List<Avaliacao> listaAvaliacaoPendenteModeracao(Aula aula) {
		return avaliacaoDAO.listaAvaliacaoPorAulaPendente(aula);
	}

	@PermitAll
	public List<Avaliacao> montarTimeLine(Aula aula) {
		if (aula.getTurma().getModerar()) {
			return this.avaliacaoDAO.listaAvaliacaoPorAulaModerado(aula);
		} else {
			return this.avaliacaoDAO.listaAvaliacaoPorAula(aula);
		}
	}

	public Boolean buscarAvaliacaoAulaAluno(Aula aula, Usuario aluno) {
		return avaliacaoDAO.buscarAvaliacaoAulaAluno(aula, aluno);
	}
	public Avaliacao exibeAvaliacaoAulaAluno(Aula aula, Usuario aluno){
		return avaliacaoDAO.exibeAvaliacaoAulaAluno(aula,aluno);
	}
	
	@PermitAll
	public List<Avaliacao> listaAvaliacoesPorAulaModerado(Aula aula) {
		return this.avaliacaoDAO.listaAvaliacaoPorAulaModerado(aula);
	}

}
