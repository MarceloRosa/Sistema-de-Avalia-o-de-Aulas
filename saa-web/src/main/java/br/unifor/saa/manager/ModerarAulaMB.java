package br.unifor.saa.manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.MeterGaugeChartModel;
import org.springframework.context.annotation.Scope;

import br.unifor.saa.business.AvaliacaoBO;
import br.unifor.saa.entity.Aula;
import br.unifor.saa.entity.Avaliacao;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.utils.MessagesUtils;
import br.unifor.saa.utils.Navigation;

/**
 * @author adrianopatrick@gmail.com
 * @since 9 de mai de 2016
 */
@RequestScoped
@Scope("session")
@Named(value = "moderarAulaManager")
@ManagedBean(name = "moderarAulaManager")
public class ModerarAulaMB {

	@Inject
	private AvaliacaoBO avaliacoesBO;

	private List<Avaliacao> avaliacoesPendentes;
	private List<Avaliacao> timeLine;
	private Avaliacao avaliacaoSelecionada;
	private Avaliacao itemTimeLineSelecionada;
	private Aula aula;
	private MeterGaugeChartModel meterGaugeModel2;
	public MeterGaugeChartModel getMeterGaugeModel2() {
		return meterGaugeModel2;
	}

	public void setMeterGaugeModel2(MeterGaugeChartModel meterGaugeModel2) {
		this.meterGaugeModel2 = meterGaugeModel2;
	}

	public String preparaModerarAula(Aula aula) {
		this.avaliacaoSelecionada = null;
		this.setAula(aula);
		this.atualizaTabelas();
		createMeterGaugeModels();
		
		//FIXME inserido para teste anonimato aluno
		/*for (Avaliacao avaliacao : avaliacoesPendentes) {
			if (avaliacao.getAnonimo()) {
				avaliacao.getAluno().setNome("Anônimo");
			}
		}*/

		return Navigation.MODERAR;
	}

	private void createMeterGaugeModels() {
		meterGaugeModel2 = initMeterGaugeModel();
		meterGaugeModel2.setSeriesColors("cc6666,cc6666,E7E658,66cc66,66cc66");
		meterGaugeModel2.setGaugeLabelPosition("bottom");
		meterGaugeModel2.setShowTickLabels(true);
		meterGaugeModel2.setLabelHeightAdjust(110);
		meterGaugeModel2.setIntervalOuterRadius(100);
	}

	private MeterGaugeChartModel initMeterGaugeModel() {
		List<Number> intervals = new ArrayList<Number>() {
			private static final long serialVersionUID = -5384132075657333962L;

			{
				add(1);
				add(2);
				add(3);
				add(4);
				add(5);
			}
		};
		
		List<Number> tickets = new ArrayList<Number>() {
			private static final long serialVersionUID = -5384132075657333962L;

			{
				add(1);
				add(2);
				add(3);
				add(4);
				add(5);
			}
		};

		return new MeterGaugeChartModel(this.getAula().getMedia() - 2, intervals, tickets);
	}

	public void publicar() {
		this.avaliacaoSelecionada.setModerado(true);
		try {
			this.avaliacoesBO.atualizar(avaliacaoSelecionada);
			this.atualizaTabelas();
		} catch (BOException e) {
			MessagesUtils.error(e.getMessage());
		}
	}

	private void atualizaTabelas() {
		this.setAvaliacoesPendentes(
				this.avaliacoesBO.listaAvaliacaoPendenteModeracao(this.aula));
		this.setTimeLine(this.avaliacoesBO.montarTimeLine(aula));
	}

	public void remover() {
		this.itemTimeLineSelecionada.setModerado(false);
		try {
			this.avaliacoesBO.atualizar(this.itemTimeLineSelecionada);
			this.atualizaTabelas();
		} catch (BOException e) {
			MessagesUtils.error(e.getMessage());
		}
	}

	public Avaliacao getItemTimeLineSelecionada() {
		return itemTimeLineSelecionada;
	}

	public void setItemTimeLineSelecionada(Avaliacao itemTimeLineSelecionada) {
		this.itemTimeLineSelecionada = itemTimeLineSelecionada;
	}

	public List<Avaliacao> getTimeLine() {
		return timeLine;
	}

	public void setTimeLine(List<Avaliacao> timeLine) {
		this.timeLine = timeLine;
	}

	public List<Avaliacao> getAvaliacoesPendentes() {
		return avaliacoesPendentes;
	}

	public void setAvaliacoesPendentes(List<Avaliacao> avaliacoesPendentes) {
		this.avaliacoesPendentes = avaliacoesPendentes;
	}

	public Avaliacao getAvaliacaoSelecionada() {
		return avaliacaoSelecionada;
	}

	public void setAvaliacaoSelecionada(Avaliacao avaliacaoSelecionada) {
		this.avaliacaoSelecionada = avaliacaoSelecionada;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

}
