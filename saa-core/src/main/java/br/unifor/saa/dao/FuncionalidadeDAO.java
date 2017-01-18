package br.unifor.saa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.saa.entity.Funcionalidade;

/**
 * @author marcelorosa2@hotmail.com
 * @since  06 de abril de 2016
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class FuncionalidadeDAO {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Funcionalidade funcionalidade) {
		em.persist(funcionalidade);
	}

	public void atualizar(Funcionalidade funcionalidade) {
		em.merge(funcionalidade);
	}
	
	public void remover(Funcionalidade funcionalidade) {
		em.remove(funcionalidade);
	}

	public Funcionalidade buscarPorId(Integer id) {
		String jpql = "select f from Funcionalidades f where f.id = :id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", id);
		return (Funcionalidade) query.getSingleResult();
	}

	public Funcionalidade buscarPorDescricao(String descricao) {
		String jpql = "select f from Funcionalidades f where f.descricao = :descricao";
		Query query = em.createQuery(jpql);
		query.setParameter("descricao", descricao);
		return (Funcionalidade) query.getSingleResult();
	}

}