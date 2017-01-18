package br.unifor.saa.utils;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import br.unifor.saa.business.TurmaBO;
import br.unifor.saa.entity.Turma;
import br.unifor.saa.entity.Usuario;

public class TurmaDataModel extends LazyDataModel<Turma> {

	private static final long serialVersionUID = 2094995525733757488L;
	private TurmaBO turmaBO;
	private Usuario usuario;
	private List<Turma> list;

	TurmaDataModel(TurmaBO turmaBO, Usuario usario) {
		this.turmaBO = turmaBO;
		this.usuario = usario;

		list = load(0, 5, null, null);
	}
	 @Override
	    public Turma getRowData(String rowKey) {
	        for(Turma turma : list) {
	            if(turma.getId().toString().equals(rowKey))
	                return turma;
	        }
	 
	        return null;
	    }

	@Override
	public List<Turma> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		list = turmaBO.listaTurmaPorAluno(usuario, first, pageSize);
		setRowCount(turmaBO.countTurmaPorAluno(usuario));
		return list;
	}

	@Override
	public List<Turma> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, Object> filters) {
		return load(first, pageSize, null, null, filters);
	}

}
