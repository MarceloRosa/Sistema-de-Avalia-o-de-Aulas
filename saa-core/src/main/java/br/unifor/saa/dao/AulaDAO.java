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

import br.unifor.saa.entity.Aula;
import br.unifor.saa.entity.Turma;
import br.unifor.saa.entity.Usuario;

/**
 * Data Access Object responsável por salvar, atualizar e listar aulas
 * @author marcelorosa2@gmail.com
 * @since 28 de mar de 2016
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class AulaDAO {

	private static final String ID_PROFESSOR = "idProfessor";
	private static final String ID_ALUNO = "idAluno";
	private static final String ID_TURMA = "idTurma";
	
	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Aula aula) {
		em.persist(aula);
	}

	public void atualizar(Aula aula) {
		em.merge(aula);
	}
	
	public Aula buscarPorId(Long id){
		return em.find(Aula.class, id);
	}
	
	public List<Aula> listaAulasPorTurma(Turma turma) {
		String jpql = "select a from Aula a where a.turma.id = :idTurma";
		TypedQuery<Aula> query = em.createQuery(jpql, Aula.class);
		query.setParameter(ID_TURMA, turma.getId());
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Aula> listaAulaPorProfessor(Usuario professor) {
		String jpql = "from Aula a where a.turma.professor.id = :idProfessor";
		Query query = em.createQuery(jpql);
		query.setParameter(ID_PROFESSOR, professor.getId());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Aula> listaAulaPorAluno(Usuario aluno) {
		String jpql = "select a from Aula a where a.turma.aluno.id = :idAluno";
		//String jpql = "select a from Aulas a join a.alunos al where al.id = :idAluno";
		Query query = em.createQuery(jpql);
		query.setParameter(ID_ALUNO, aluno.getId());
		return query.getResultList();
	}

	
}
