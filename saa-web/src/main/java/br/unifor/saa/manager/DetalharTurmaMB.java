package br.unifor.saa.manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.context.annotation.Scope;

import br.unifor.saa.business.AulaBO;
import br.unifor.saa.business.TurmaBO;
import br.unifor.saa.business.UsuarioBO;
import br.unifor.saa.entity.Aula;
import br.unifor.saa.entity.Turma;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.entity.enums.TipoPapel;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.to.SegurancaTO;
import br.unifor.saa.utils.MessagesUtils;
import br.unifor.saa.utils.Navigation;

@RequestScoped
@Scope("session")
@Named(value = "detalharTurmaManager")
@ManagedBean(name = "detalharTurmaManager")
public class DetalharTurmaMB {

	@Inject
	private AulaBO aulaBO;
	@Inject
	private TurmaBO turmaBO;
	@Inject
	private SegurancaTO segurancaTO;
	@Inject
	private UsuarioBO usuarioBO;
	private List<Usuario> alunos;
	private Turma turma;
	private List<Aula> aulas;
	private Usuario alunoSelecionado;
	private static Long sequencia;
	private Aula aulaSelecionada;

	private LineChartModel animatedModel;

	public String preparaDetalhar() {
		this.aulaSelecionada = null;
		this.alunoSelecionado = null;
		this.turma = this.turmaBO.buscarTurmaPorId(this.turma.getId());
		this.aulas = this.aulaBO.listarAulasPorTurma(this.turma);
		this.aulas.sort((o1, o2) -> o1.getDataAula()
				.compareTo(o2.getDataAula()));
		sequencia = 1L;
		this.aulas.forEach(aula -> {
			aula.setSequencia(sequencia++);
		});
		if (this.segurancaTO.getUsuario().maxPapel()
				.equals(TipoPapel.PROFESSOR)
				&& montaSerie(this.aulas)) {
			this.createAnimatedModels();
		} else {
			this.animatedModel = null;
		}
		this.turma.getAlunos().sort(
				(o1, o2) -> o1.getEmail().compareTo(o2.getEmail()));
		this.alunos = new ArrayList<>();
		return Navigation.DETALHAR;
	}
	
	public String preparaDetalhar(Turma turma) {
		this.turma = this.turmaBO.buscarTurmaPorId(turma.getId());
		this.aulas = this.aulaBO.listarAulasPorTurma(this.turma);
		this.aulas.sort((o1, o2) -> o1.getDataAula()
				.compareTo(o2.getDataAula()));
		sequencia = 1L;
		this.aulas.forEach(aula -> {
			aula.setSequencia(sequencia++);
		});
		if (this.segurancaTO.getUsuario().maxPapel()
				.equals(TipoPapel.PROFESSOR)
				&& montaSerie(this.aulas)) {
			this.createAnimatedModels();
		} else {
			this.animatedModel = null;
		}
		this.turma.getAlunos().sort(
				(o1, o2) -> o1.getEmail().compareTo(o2.getEmail()));
		this.alunos = new ArrayList<>();
		return Navigation.DETALHAR;
	}

	/**
	 * 
	 */
	private void createAnimatedModels() {
		animatedModel = initLinearModel();
		animatedModel.setTitle("Média das Aulas");
		animatedModel.setAnimate(true);
		animatedModel.setLegendPosition("se");
		animatedModel.setShowPointLabels(true);
		animatedModel.getAxes().put(AxisType.X, new CategoryAxis("Aulas"));
		Axis yAxis = animatedModel.getAxis(AxisType.Y);
		yAxis.setLabel("Notas");
		yAxis.setMin(0);
		yAxis.setMax(6);
	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries serie = new LineChartSeries();
		serie.setLabel("Média Avaliações/Aula");

		this.aulas.forEach((aula) -> {
			this.aulas.stream().count();
			serie.set(aula.getSequencia(), aula.getMedia());
		});

		model.addSeries(serie);

		return model;
	}

	private boolean montaSerie(List<Aula> aulas) {
		return aulas.stream().anyMatch((aula) -> aula.getMedia() != 0.0);
	}

	public List<Usuario> completeAluno(String query) {
		List<Usuario> usuarios = this.usuarioBO.buscarPorNomeEmail(query
				.toLowerCase());
		List<Usuario> alunos = new ArrayList<>();

		usuarios.forEach((u) -> {
			if (u.maxPapel().equals(TipoPapel.ALUNO)
					&& !this.turma.getAlunos().contains(u)) {
				alunos.add(u);
			}
		});

		return alunos;
	}

	public String adicionaAlunos() {
		if (!this.alunos.isEmpty()) {
			if (this.turma.getAlunos() != null) {
				this.turma.getAlunos().addAll(this.alunos);
			} else {
				this.turma.setAlunos(this.alunos);
			}
			try {
				this.turmaBO.atualizar(turma);
				MessagesUtils.info("Aluno(s) adicionado(s) com sucesso!");
			} catch (BOException e) {
				MessagesUtils.error(e.getMessage());
			}
		} else {
			MessagesUtils.warn("Nenhum aluno selecionado.");
		}
		return preparaDetalhar();
	}

	public void excluirAluno() {
		if (this.alunoSelecionado != null) {
			if (this.turma.getAlunos() != null
					&& !this.turma.getAlunos().isEmpty()) {
				this.turma.getAlunos().remove(this.alunoSelecionado);
				try {
					this.turmaBO.atualizar(turma);
					MessagesUtils.info(this.alunoSelecionado.getNome()
							+ " foi excluido da turma.");
				} catch (BOException e) {
					MessagesUtils
							.error("N�o foi poss�vel exlcluir o aluno da turma.");
				}
			}
		} else {
			MessagesUtils.error("Nenhum aluno selecionado.");
		}
	}

	public void excluirAula() {
		if (this.aulaSelecionada != null) {
			if (this.aulas.getClass() != null) {
				this.aulas.remove(this.aulaSelecionada);
				try {
					this.aulaBO.atualizar(aulaSelecionada);
					MessagesUtils.info("A aula, "+this.aulaSelecionada.getDescricao()+ " foi excluída da turma.");
				} catch (BOException e) {
					MessagesUtils.error("Não foi possível exlcluir a aula da turma.");
				}
			}
		} else {
			MessagesUtils.error("Nenhuma aula selecionado.");
		}
	}
	public int getAlunoSize() {
		return this.turma.getAlunos().size();
	}

	public LineChartModel getAnimatedModel() {
		return animatedModel;
	}

	public void setAnimatedModel(LineChartModel animatedModel) {
		this.animatedModel = animatedModel;
	}

	public Usuario getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Usuario alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}

	public List<Usuario> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Usuario> alunos) {
		this.alunos = alunos;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	public AulaBO getAulaBO() {
		return aulaBO;
	}

	public void setAulaBO(AulaBO aulaBO) {
		this.aulaBO = aulaBO;
	}

	public Aula getAulaSelecionada() {
		return aulaSelecionada;
	}

	public void setAulaSelecionada(Aula aulaSelecionada) {
		this.aulaSelecionada = aulaSelecionada;
	}

}
