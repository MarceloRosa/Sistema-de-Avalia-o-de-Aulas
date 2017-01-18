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
import br.unifor.saa.entity.Avaliacao;
import br.unifor.saa.entity.Usuario;

/**
 * Data Access Object responsável por salvar, atualizar e listar avaliação.
 * @author adrianopatrick@gmail.com
 * @since 16 de mar de 2016
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class AvaliacaoDAO {

	@PersistenceContext
	private EntityManager em;

	private static final String ID_ALUNO = "idAluno";
	private static final String ID_AULA = "idAula";

	public void salvar(Avaliacao avaliacao) {
		em.persist(avaliacao);
	}

	public void atualizar(Avaliacao avaliacao) {
		em.merge(avaliacao);
	}

	@SuppressWarnings("unchecked")
	public List<Avaliacao> listaAvaliacaoPorAluno(Usuario aluno) {
		String jpql = "from Avaliacao a where a.aluno.id = :idAluno";
		Query query = em.createQuery(jpql);
		query.setParameter(ID_ALUNO, aluno.getId());
		return query.getResultList();
	}
	
	public List<Avaliacao> listaAvaliacaoPorAulaPendente(Aula aula) {
		String jpql = "from Avaliacao a where a.aula.id = :idAula and a.moderado = false";
		TypedQuery<Avaliacao> query = em.createQuery(jpql, Avaliacao.class);
		query.setParameter(ID_AULA, aula.getId());
		return query.getResultList();
	}
	
	public List<Avaliacao> listaAvaliacaoPorAulaModerado(Aula aula){
		String jpql = "from Avaliacao a where a.aula.id = :idAula and a.moderado = true";
		TypedQuery<Avaliacao> query = em.createQuery(jpql, Avaliacao.class);
		query.setParameter(ID_AULA, aula.getId());
		return query.getResultList();
	}
	
	public List<Avaliacao> listaAvaliacaoPorAula(Aula aula){
		String jpql = "from Avaliacao a where a.aula.id = :idAula";
		TypedQuery<Avaliacao> query = em.createQuery(jpql, Avaliacao.class);
		query.setParameter(ID_AULA, aula.getId());
		return query.getResultList();
	}

	public Boolean buscarAvaliacaoAulaAluno(Aula aula, Usuario aluno) {
		String jpql = "from Avaliacao a where a.aula.id = :idAula and a.aluno.id= :idAluno";
		TypedQuery<Avaliacao> query = em.createQuery(jpql, Avaliacao.class);
		query.setParameter(ID_AULA, aula.getId());
		query.setParameter(ID_ALUNO,aluno.getId());
		
		try {
			query.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Avaliacao exibeAvaliacaoAulaAluno(Aula aula, Usuario aluno) {
		String jpql = "from Avaliacao a where a.aula.id = :idAula and a.aluno.id= :idAluno";
		TypedQuery<Avaliacao> query = em.createQuery(jpql, Avaliacao.class);
		query.setParameter(ID_AULA, aula.getId());
		query.setParameter(ID_ALUNO,aluno.getId());
		Avaliacao avaliacao = query.getSingleResult();
		return avaliacao;
	}

}
