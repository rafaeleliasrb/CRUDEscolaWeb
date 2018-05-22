package br.com.escola.model.service;

import java.util.List;

import br.com.escola.model.dao.ProfessorDao;
import br.com.escola.model.domain.Professor;

public class ProfessorService {
	
	private ProfessorDao dao = new ProfessorDao();
	
	public ProfessorService() {
		popularBanco();
	}
	
	private void popularBanco() {
		Professor professor = new Professor();
		professor.setId(0);
		professor.setNome("Gabriel");
		professor.setEmail("gabriel@gmail.com");
		professor.setDisciplina("Programação em Java");
		professor.setCargaHoraria(45f);
		
		Professor professor2 = new Professor();
		professor2.setId(0);
		professor2.setNome("Elias");
		professor2.setEmail("elias@gmail.com");
		professor2.setDisciplina("Frameworks em Java");
		professor2.setCargaHoraria(15f);
		
		salvar(professor);
		salvar(professor2);
	}
	
	public void salvar(Professor professor) {
		if(professor != null) {
			if(professor.getId() > 0) {
				dao.alterar(professor);
			} else {
				dao.inserir(professor);
			}
		}
	}
	
	public List<Professor> listar() {
		return this.dao.listar();
	}

	public Professor buscar(int id) {
		return this.dao.buscar(id);
	}
	
	public Professor buscar(String nome) {
		return this.dao.buscar(nome);
	}

	public void imprimir(List<Professor> list) {
		System.out.println("-- Professor:");
		for (Professor p : list) {
			System.out.println(p);
		}
	}

}
