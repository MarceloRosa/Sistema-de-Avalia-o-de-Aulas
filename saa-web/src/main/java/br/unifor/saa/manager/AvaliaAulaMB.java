package br.unifor.saa.manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.unifor.saa.business.AvaliacaoBO;
import br.unifor.saa.entity.Aula;
import br.unifor.saa.entity.Avaliacao;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.to.SegurancaTO;
import br.unifor.saa.utils.MessagesUtils;
import br.unifor.saa.utils.Navigation;

@RequestScoped
@Scope("session")
@Named(value = "avaliaAulaManager")
@ManagedBean(name = "avaliaAulaManager")
public class AvaliaAulaMB {

	@Inject
	private AvaliacaoBO avaliacaoBO;
	@Inject
	private SegurancaTO segurancaTO;
	@Inject
	private DetalharTurmaMB detalharTurmaMB;
	private Avaliacao avaliacao;
	private Aula aula;
	
	public String preparaAvaliaAula(Aula aula){
		Boolean avaliou = avaliacaoBO.buscarAvaliacaoAulaAluno(aula,segurancaTO.getUsuario());
		if (avaliou){
			MessagesUtils.warn("Aula já avaliada.");
			return Navigation.FRACASSO;
		}
		this.aula = aula;
		avaliacao = new Avaliacao();
		avaliacao.setAula(this.aula);
		avaliacao.setAluno(segurancaTO.getUsuario());
		avaliacao.setModerado(false);
		return Navigation.AVALIA;
	}
	
	public String salvar(){
		try {
			avaliacaoBO.salvar(avaliacao);
		} catch (BOException e) {
			MessagesUtils.error(e.getMessage());
			return Navigation.FRACASSO;
		}
		
		return detalharTurmaMB.preparaDetalhar();
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	/**
	 * @return the avaliacao
	 */
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	/**
	 * @param avaliacao the avaliacao to set
	 */
	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

}
