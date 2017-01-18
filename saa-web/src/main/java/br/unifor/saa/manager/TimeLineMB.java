package br.unifor.saa.manager;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.unifor.saa.business.AvaliacaoBO;
import br.unifor.saa.entity.Aula;
import br.unifor.saa.entity.Avaliacao;
import br.unifor.saa.to.SegurancaTO;
import br.unifor.saa.utils.MessagesUtils;
import br.unifor.saa.utils.Navigation;

@RequestScoped
@Scope("session")
@Named(value = "timeLineManager")
@ManagedBean(name = "timeLineManager")
public class TimeLineMB {

	@Inject
	private AvaliacaoBO avaliacaoBO;
	@Inject
	private SegurancaTO segurancaTO;
	private Avaliacao avaliacao;
	private Avaliacao avaliacaoSelecionada;
	private Aula aula;
	private List<Avaliacao> timeLine;
	
	public String preparaTimeline(Aula aula){
//		avaliacaoSelecionada = null;
		Boolean avaliou = avaliacaoBO.buscarAvaliacaoAulaAluno(aula,segurancaTO.getUsuario());
		if (!avaliou){
			MessagesUtils.warn("Aula ainda não avaliada!");
			return Navigation.FRACASSO;
		}
		
		this.setAula(aula);
		this.setAvaliacao(this.avaliacaoBO.exibeAvaliacaoAulaAluno(aula, segurancaTO.getUsuario()));
		this.setTimeLine(this.avaliacaoBO.montarTimeLine(aula));
		
		return Navigation.AVALIADA;
	}

//	private Avaliacao getAvaliacao(Aula aula2, Usuario usuario) {
//		// TODO Auto-generated method stub
//		return null;
//	}

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

	/**
	 * @return the aula
	 */
	public Aula getAula() {
		return aula;
	}

	/**
	 * @param aula the aula to set
	 */
	public void setAula(Aula aula) {
		this.aula = aula;
	}

	/**
	 * @return the timeLine
	 */
	public List<Avaliacao> getTimeLine() {
		return timeLine;
	}

	/**
	 * @param timeLine the timeLine to set
	 */
	public void setTimeLine(List<Avaliacao> timeLine) {
		this.timeLine = timeLine;
	}

	/**
	 * @return the avaliacaoSelecionada
	 */
	public Avaliacao getAvaliacaoSelecionada() {
		return avaliacaoSelecionada;
	}

	/**
	 * @param avaliacaoSelecionada the avaliacaoSelecionada to set
	 */
	public void setAvaliacaoSelecionada(Avaliacao avaliacaoSelecionada) {
		this.avaliacaoSelecionada = avaliacaoSelecionada;
	}

}
