package br.unifor.saa.business;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.saa.aspectj.PermitAll;
import br.unifor.saa.dao.FuncionalidadeDAO;
import br.unifor.saa.entity.Funcionalidade;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.util.MessagesResources;

/**
 * @author marcelorosa2@hotmail.com
 * @since  06 de abril de 2016
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class FuncionalidadeBO {

	@Inject
	private FuncionalidadeDAO funcionalidadeDAO;

	@PermitAll
	public void salvar(Funcionalidade funcionalidade) throws BOException {

		Funcionalidade func = this.funcionalidadeDAO.buscarPorDescricao(funcionalidade.getDescricao());
		if (func != null) {
			throw new BOException(MessagesResources.getMessages("erro_duplicate_funcionalidade"));
		}
		
		this.funcionalidadeDAO.salvar(funcionalidade);

	}

	@PermitAll
	public void atualizar(Funcionalidade funcionalidade) {
		this.funcionalidadeDAO.atualizar(funcionalidade);
	}
	@PermitAll
	public Funcionalidade buscaPorId(Integer id){
		return this.funcionalidadeDAO.buscarPorId(id);
	}

	public Funcionalidade buscaPorDescricao(String descricao){
		return this.funcionalidadeDAO.buscarPorDescricao(descricao);
	}
}