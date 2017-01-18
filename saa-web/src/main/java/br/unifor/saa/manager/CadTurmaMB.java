package br.unifor.saa.manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.unifor.saa.business.TurmaBO;
import br.unifor.saa.business.UsuarioBO;
import br.unifor.saa.entity.Turma;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.entity.enums.TipoPapel;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.to.SegurancaTO;
import br.unifor.saa.utils.MessagesUtils;
import br.unifor.saa.utils.Navigation;

@RequestScoped
@Scope("session")
@Named(value = "cadTurmaManager")
@ManagedBean(name = "cadTurmaManager")
public class CadTurmaMB {

	private String disciplina;
	private String descricao;
	private String instituicao;
	private String semestre;
	private List<Usuario> alunos;
	private Boolean ativo;
	private Boolean moderar;

	@Inject
	private TurmaBO turmaBO;
	@Inject
	private SegurancaTO segurancaTO;
	@Inject
	private UsuarioBO usuarioBO;
	
	@Inject
	private ListaTurmaMB listaTurmaMB;

	public String preparaCadastro() {
		this.limparCampos();
		return Navigation.SUCESSO;
	}

	public String salvar() {

		Turma turma = new Turma();
		turma.setDescricao(descricao);
		turma.setDisciplina(disciplina);
		turma.setInstituicao(instituicao);
		turma.setSemestre(semestre);
		turma.setAlunos(alunos);
		turma.setAtivo(ativo);
		turma.setModerar(moderar);
		turma.setProfessor(segurancaTO.getUsuario());

		try {
			turmaBO.salvar(turma);
			MessagesUtils.info("Turma "+turma.getDisciplina()+" salva com sucesso!");
		} catch (BOException e) {
			MessagesUtils.error(e.getMessage());
			return Navigation.FRACASSO;
		}

		return this.listaTurmaMB.preparaListTurma();
	}

	public List<Usuario> completeAluno(String query) {
		List<Usuario> usuarios = this.usuarioBO
				.buscarPorNomeEmail(query.toLowerCase());
		List<Usuario> alunos = new ArrayList<>();
		
		for (Usuario usuario : usuarios) {
			if(usuario.maxPapel().equals(TipoPapel.ALUNO)){
				alunos.add(usuario);
			}
		}
		
		return alunos;
	}

	private void limparCampos() {
		this.disciplina = "";
		this.descricao = "";
		this.instituicao = "";
		this.semestre = "";
		this.alunos = new ArrayList<>();
		this.ativo = true;
		this.moderar = true;

	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Usuario> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Usuario> alunos) {
		this.alunos = alunos;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getModerar() {
		return moderar;
	}

	public void setModerar(Boolean moderar) {
		this.moderar = moderar;
	}

	public TurmaBO getTurmaBO() {
		return turmaBO;
	}

	public void setTurmaBO(TurmaBO turmaBO) {
		this.turmaBO = turmaBO;
	}

	public SegurancaTO getSegurancaTO() {
		return segurancaTO;
	}

	public void setSegurancaTO(SegurancaTO segurancaTO) {
		this.segurancaTO = segurancaTO;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

}
