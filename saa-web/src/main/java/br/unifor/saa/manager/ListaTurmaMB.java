package br.unifor.saa.manager;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.unifor.saa.business.TurmaBO;
import br.unifor.saa.entity.Turma;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.to.SegurancaTO;
import br.unifor.saa.utils.MessagesUtils;
import br.unifor.saa.utils.Navigation;
import br.unifor.saa.utils.TurmaDataModelBuilder;
import br.unifor.saa.utils.TurmaProfessorDataModel;

@RequestScoped
@Scope("session")
@Named(value = "listaTurmaManager")
@ManagedBean(name = "listaTurmaManager")
public class ListaTurmaMB {
	
	@Inject
	private TurmaBO turmaBO;
	@Inject
	private SegurancaTO segurancaTO;
	
	private List<Turma> turmas;
	private Turma turmaSelecionada;
	private TurmaProfessorDataModel turmaProfessorDataModel;

	@PostConstruct
	public String preparaListTurma(){
//		this.turmaSelecionada = null;
		turmaProfessorDataModel = TurmaDataModelBuilder.getInstance().createTurmaProfessorDataModel(turmaBO, segurancaTO.getUsuario());
//		turmas = turmaBO.listaTurmaPorProfessor(segurancaTO.getUsuario());
		return Navigation.HOME_PROFESSOR;
	}
	
	
	
	public TurmaProfessorDataModel getTurmaProfessorDataModel() {
		return turmaProfessorDataModel;
	}

	public void setTurmaProfessorDataModel(TurmaProfessorDataModel turmaProfessorDataModel) {
		this.turmaProfessorDataModel = turmaProfessorDataModel;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}
	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Turma getTurmaSelecionada() {
		return turmaSelecionada;
	}

	public void setTurmaSelecionada(Turma turmaSelecionada) {
		this.turmaSelecionada = turmaSelecionada;
	}
	
	public void excluirTurma(Turma turmaSelecionada) throws BOException {
		if (turmaSelecionada != null) {
				this.turmas.remove(turmaSelecionada);
				try {
					MessagesUtils.info("A turma: " + this.turmaSelecionada.getDescricao()+ " foi excluida com sucesso.");
					this.turmaBO.atualizar((Turma) turmas);
				} catch (BOException e) {
					MessagesUtils
							.error("Não foi possivel exlcluir a turma.");
				}
			}
		 else {
			MessagesUtils.error("Nenhuma turma selecionado.");
		}
	}
	

}
