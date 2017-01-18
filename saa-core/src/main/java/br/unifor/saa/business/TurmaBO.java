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
import br.unifor.saa.dao.TurmaDAO;
import br.unifor.saa.entity.Turma;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.util.MessagesResources;

/**
 * Bussiness Object responsável por salvar, atualizar, contar e listar turmas
 * @author adrianopatrick@gmail.com
 * @since 16 de mar de 2016
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TurmaBO {

	@Inject
	private TurmaDAO turmaDAO;

	@PermitAll
	public List<Turma> listaTurmaPorProfessor(Usuario professor) {
		return turmaDAO.listaTurmaPorProfessor(professor);
	}
	
	@PermitAll
	public List<Turma> listaTurmaPorAluno(Usuario aluno) {
		return turmaDAO.listaTurmaPorAluno(aluno);
	}
	
	@PermitAll
	public Turma buscarTurmaPorId(Long id){
		return this.turmaDAO.buscarPorId(id);
	}

	//@RolesAllowed("CADASTRAR_TURMA")
	@PermitAll
	public void salvar(Turma turma) throws BOException {
		try {
			this.turmaDAO.salvar(turma);
		} catch (Exception e) {
			throw new BOException(MessagesResources.getMessages("cadTurma.msg_salvar_error"));
		}
	}
	
	public void atualizar(Turma turma) throws BOException {
		try {
			this.turmaDAO.atualizar(turma);
		} catch (Exception e) {
			throw new BOException(MessagesResources.getMessages("cadTurma.msg_atualizar_error"));
		}
	}

	public List<Turma> listaTurmaPorAluno(Usuario usuario, int first, int pageSize) {
		return turmaDAO.listaTurmaPorAluno(usuario, first, pageSize);
		
	}
	

	public Integer countTurmaPorAluno(Usuario usuario) {
		return turmaDAO.countTurmaPorAluno(usuario);
	}
	
	public List<Turma> listaTurmaPorProfessor(Usuario usuario, int first, int pageSize) {
		return turmaDAO.listaTurmaPorProfessor(usuario, first, pageSize);
		
	}
	

	public Integer countTurmaPorProfessor(Usuario usuario) {
		return turmaDAO.countTurmaPorProfessor(usuario);
	}

}
