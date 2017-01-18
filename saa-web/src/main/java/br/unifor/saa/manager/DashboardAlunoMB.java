package br.unifor.saa.manager;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.context.annotation.Scope;

import br.unifor.saa.business.AvaliacaoBO;
import br.unifor.saa.business.TurmaBO;
import br.unifor.saa.entity.Avaliacao;
import br.unifor.saa.entity.Turma;
import br.unifor.saa.to.SegurancaTO;
import br.unifor.saa.utils.Navigation;
import br.unifor.saa.utils.TurmaDataModel;
import br.unifor.saa.utils.TurmaDataModelBuilder;

@RequestScoped
@Scope("session")
@Named(value = "dashboardAlunoManager")
@ManagedBean(name = "dashboardAlunoManager")
public class DashboardAlunoMB {
	
	@Inject
	private TurmaBO turmaBO;
	
	@Inject 
	private AvaliacaoBO avaliacaoBO;
	
	@Inject
	private SegurancaTO segurancaTO;
	
	private List<Turma> turmas;
	private Turma turma;
	private TurmaDataModel turmaDataModel;
	private List<Avaliacao> avaliacoes;
	
	public String preparaDashboard(){
		init();
		return Navigation.HOME_ALUNO;
	}
	
	
	@PostConstruct
	public void init(){
		preparaListTurma();
		preparaListAvaliacoes();
	}
	

	public String preparaListTurma(){
		this.turma = null;
		turmaDataModel = TurmaDataModelBuilder.getInstance().createTurmaDataModel(turmaBO, segurancaTO.getUsuario());
		
//		turmas = turmaBO.listaTurmaPorAluno(segurancaTO.getUsuario());
		return Navigation.SUCESSO;
	}
	
	public String preparaListAvaliacoes(){
		avaliacoes = avaliacaoBO.listaAvaliacaoPorAluno(segurancaTO.getUsuario());
		return Navigation.SUCESSO;
	}
	public void onRowSelect(SelectEvent event) {
		turma = (Turma) event.getObject();
	}
 
    public void onRowUnselect(UnselectEvent event) {
    	turma = null;
    }

	/**
	 * @return the turmas
	 */
	public List<Turma> getTurmas() {
		return turmas;
	}


	/**
	 * @param turmas the turmas to set
	 */
	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}


	/**
	 * @return the turma
	 */
	public Turma getTurma() {
		return turma;
	}


	/**
	 * @param turma the turma to set
	 */
	public void setTurma(Turma turma) {
		this.turma = turma;
	}


	/**
	 * @return the turmaDataModel
	 */
	public TurmaDataModel getTurmaDataModel() {
		return turmaDataModel;
	}


	/**
	 * @param turmaDataModel the turmaDataModel to set
	 */
	public void setTurmaDataModel(TurmaDataModel turmaDataModel) {
		this.turmaDataModel = turmaDataModel;
	}


	/**
	 * @return the avaliacoes
	 */
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}


	/**
	 * @param avaliacoes the avaliacoes to set
	 */
	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	


	
}
