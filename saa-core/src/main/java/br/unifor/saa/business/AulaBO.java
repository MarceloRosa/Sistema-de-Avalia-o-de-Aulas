/**
 * 
 */
package br.unifor.saa.business;

import java.util.List;
import java.util.OptionalDouble;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.saa.aspectj.PermitAll;
import br.unifor.saa.aspectj.RolesAllowed;
import br.unifor.saa.dao.AulaDAO;
import br.unifor.saa.dao.AvaliacaoDAO;
import br.unifor.saa.entity.Aula;
import br.unifor.saa.entity.Avaliacao;
import br.unifor.saa.entity.Turma;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.util.MessagesResources;

/**
 * Bussiness Object responsável por cadastrar e listar aula
 * @author adrianopatrick@gmail.com
 * @since 16 de mar de 2016
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AulaBO {

	@Inject
	private AulaDAO aulaDAO;
	
	@Inject
	private AvaliacaoDAO avaliacaoDAO;

	@PermitAll
	public List<Aula> listaAulaPorProfessor(Usuario professor) {
		return aulaDAO.listaAulaPorProfessor(professor);
	}
	
	@PermitAll
	public Aula buscarPorId(Long id) {
		return aulaDAO.buscarPorId(id);
	}
	
	@PermitAll
	public List<Aula> listaAulaPorAluno(Usuario aluno) {
		return aulaDAO.listaAulaPorAluno(aluno);
	}

	@RolesAllowed("CADASTRAR_AULA")
	public void salvar(Aula aula) throws BOException {
		try {
			this.aulaDAO.salvar(aula);
		} catch (Exception e) {
			throw new BOException(MessagesResources.getMessages("cadAula.msg_salvar_error"));
		}
	}

	@PermitAll
	public List<Aula> listarAulasPorTurma(Turma turma) {
		
		List<Aula> aulas = aulaDAO.listaAulasPorTurma(turma);
		aulas.forEach((aula) -> aula.setMedia(this.calcularMedia(aula)));
		
		return aulas;
	}
	public void atualizar(Aula aula) throws BOException {
		try {
			this.aulaDAO.atualizar(aula);
		} catch (Exception e) {
			throw new BOException(MessagesResources.getMessages("cadAula.msg_atualizar_error"));
		}
	}
	private Double calcularMedia(Aula aula) {
		List<Avaliacao> avaliacoes = avaliacaoDAO.listaAvaliacaoPorAula(aula);
		OptionalDouble media = avaliacoes.stream()
				.mapToDouble(avaliacao -> avaliacao.getPontuacao()).average();
		return media.isPresent() ? media.getAsDouble() : 0;
	}

}
