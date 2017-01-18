/**
 * 
 */
package br.unifor.saa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.saa.entity.Turma;
import br.unifor.saa.entity.Usuario;

/**
 * Data Access Object responsável por salvar, atualizar, buscar e listar turma.
 * @author adrianopatrick@gmail.com
 * @since 16 de mar de 2016
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class TurmaDAO {

	@PersistenceContext
	private EntityManager em;

	private static final String ID_PROFESSOR = "idProfessor";
	private static final String ID_ALUNO = "idAluno";
	private static final String ID_TURMA = "idTurma";

	public void salvar(Turma turma) {
		em.persist(turma);
	}

	public void atualizar(Turma turma) {
		em.merge(turma);
	}

	public Turma buscarPorId(Long id) {
		String jpql = "select t from Turma t join fetch t.alunos where t.id = :idTurma";
		TypedQuery<Turma> query = em.createQuery(jpql, Turma.class);
		query.setParameter(ID_TURMA, id);
		return query.getSingleResult();
	}

	public List<Turma> listaTurmaPorProfessor(Usuario professor) {
		String jpql = "select t from Turma t join fetch t.alunos where t.professor.id = :idProfessor and t.ativo = true order by t.id desc";
		TypedQuery<Turma> query = em.createQuery(jpql, Turma.class);
		query.setParameter(ID_PROFESSOR, professor.getId());
		return query.getResultList();
	}
	/**
	 * criado para fazer o paginator 
	 * @param professor
	 * @param firstResult
	 * @param pageSize
	 * @return
	 */
	public List<Turma> listaTurmaPorProfessor(Usuario professor, Integer firstResult, Integer pageSize) {
		String jpql = "select t from Turma t where t.professor.id = :idProfessor and t.ativo = true order by t.id desc";
		TypedQuery<Turma> query = em.createQuery(jpql, Turma.class);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		query.setParameter(ID_PROFESSOR, professor.getId());
		return query.getResultList();
	}
	/**
	 * criado para fazer o paginator 
	 * @param professor
	 * @return
	 * 
	 */
	public Integer countTurmaPorProfessor(Usuario professor) {
		String jpql = "select count(t) from Turma t where t.professor.id = :idProfessor and t.ativo = true";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter(ID_PROFESSOR, professor.getId());
		return query.getSingleResult().intValue();
	}

	@SuppressWarnings("unchecked")
	public List<Turma> listaTurmaPorAluno(Usuario aluno) {
		String jpql = "select t from Turma t join fetch t.alunos a where a.id IN :idAluno";
		Query query = em.createQuery(jpql);
		query.setParameter(ID_ALUNO, aluno.getId());
		return query.getResultList();
	}

	public List<Turma> listaTurmaPorAluno(Usuario usuario, int first,
			int pageSize) {
		String jpql = "select t from Turma t join t.alunos a where a.id IN :idAluno";
		TypedQuery<Turma> query = em.createQuery(jpql, Turma.class);
		query.setParameter(ID_ALUNO, usuario.getId());
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	public Integer countTurmaPorAluno(Usuario usuario) {
		String jpql = "select count(t) from Turma t join t.alunos a where a.id IN :idAluno";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter(ID_ALUNO, usuario.getId());
		return query.getSingleResult().intValue();
	}

}
