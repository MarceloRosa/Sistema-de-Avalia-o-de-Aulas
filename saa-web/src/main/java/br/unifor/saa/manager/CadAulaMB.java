package br.unifor.saa.manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.unifor.saa.business.AulaBO;
import br.unifor.saa.entity.Aula;
import br.unifor.saa.entity.Turma;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.utils.MessagesUtils;
import br.unifor.saa.utils.Navigation;

@RequestScoped
@Scope("session")
@Named(value = "cadAulaManager")
@ManagedBean(name = "cadAulaManager")
public class CadAulaMB {

	@Inject
	private AulaBO aulaBO;
	@Inject
	private DetalharTurmaMB detalharTurmaMB;
	private Aula aula;
	
	public String preparaCadAula(Turma turma){
		aula = new Aula();
		aula.setTurma(turma);
		return Navigation.CADASTRO;
	}
	
	public String salvar(){
		try {
			aula.setAtivo(true);
			aulaBO.salvar(aula);
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

}
