/**
 * 
 */
package br.unifor.saa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.saa.entity.Papel;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.entity.enums.TipoPapel;

/**
 * Data Access Object responsável por salvar, atualizar, buscar e listar usuário.
 * @author adrianopatrick@gmail.com
 * @since 10 de dez de 2015
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Usuario usuario) {
		em.persist(usuario);
	}

	public void atualizar(Usuario usuario) {
		em.merge(usuario);
	}
	
	public Usuario buscarPorId(Long id){
		return em.find(Usuario.class, id);
	}

	public Usuario buscarPorEmailSenha(String email, String senha) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder
				.createQuery(Usuario.class);
		Root<Usuario> Usuario = criteriaQuery.from(Usuario.class);
		Predicate restriction = criteriaBuilder.and(
				criteriaBuilder.equal(Usuario.<String> get("email"), email),
				criteriaBuilder.equal(Usuario.<String> get("senha"), senha));
		criteriaQuery.where(criteriaBuilder.and(restriction));

		Query query = em.createQuery(criteriaQuery);
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario buscarPorEmail(String email) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder
				.createQuery(Usuario.class);
		Root<Usuario> Usuario = criteriaQuery.from(Usuario.class);
		criteriaQuery.where(criteriaBuilder.equal(
				Usuario.<String> get("email"), email));

		Query query = em.createQuery(criteriaQuery);
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Usuario> buscarPorNomeEmail(String email){
		String jpql = "select u from Usuario u where lower(u.email) like :email";
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setParameter("email", email + "%");
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Papel buscarPapelPorTipoPapel(TipoPapel tipoPapel) {
		String jpql = "select p from Papel p where p.tipoPapel = :tipoPapel";
		Query query = em.createQuery(jpql);
		query.setParameter("tipoPapel", tipoPapel);
		return (Papel) query.getSingleResult();
	}

}
