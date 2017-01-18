package br.unifor.saa.utils;

import br.unifor.saa.business.TurmaBO;
import br.unifor.saa.entity.Usuario;

public final class TurmaDataModelBuilder {

	private static TurmaDataModelBuilder instance;

	private TurmaDataModelBuilder() {
		
	}
	
	public static TurmaDataModelBuilder getInstance() {
		if (instance == null) {
			instance = new TurmaDataModelBuilder();
		}
		return instance;
	}
	
	public TurmaDataModel createTurmaDataModel(TurmaBO turmaBO, Usuario usario) {
		return new TurmaDataModel(turmaBO, usario);
	}
	public TurmaProfessorDataModel createTurmaProfessorDataModel(TurmaBO turmaBO, Usuario usario) {
		return new TurmaProfessorDataModel(turmaBO, usario);
	}
}
