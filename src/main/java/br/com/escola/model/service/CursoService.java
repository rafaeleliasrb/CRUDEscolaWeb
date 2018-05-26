package br.com.escola.model.service;

import java.util.List;

import br.com.escola.model.dao.CursoDao;
import br.com.escola.model.domain.Curso;

public class CursoService {

	private CursoDao dao = new CursoDao();
	
	public List<Curso> listar() {
		return this.dao.listar();
	}
	
	public Curso buscar(int id) {
		return this.dao.buscar(id);
	}
	
	public void salvar(Curso curso) {
		if(curso != null) {
			if(curso.getIdInt() > 0) {
				dao.alterar(curso);
			} else {
				dao.inserir(curso);
			}
		}
	}
}
