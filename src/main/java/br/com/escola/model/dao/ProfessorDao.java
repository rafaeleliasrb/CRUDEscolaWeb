package br.com.escola.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.escola.model.domain.Professor;

public class ProfessorDao {
	
	private List<Professor> BD = new ArrayList<>();
	
	
	public List<Professor> listar() {
		return BD;
	}
	
	public Professor buscar(int id) {
		for (Professor professor : BD) {
			if(professor.getId() == id) {
				return professor;
			}
		}
		return null;
	}
	
	public Professor buscar(String nome) {
		for (Professor professor : BD) {
			if(professor.getNome() == nome) {
				return professor;
			}
		}
		return null;
	}
	
	public void inserir(Professor professor) {
		professor.setId(BD.size()+1);
		BD.add(professor);
	}
	
	public void alterar(Professor professor) {
		int indexBD = getIndexProfessor(professor.getId());
		boolean foiEncontrado = indexBD > 0;
		
		if(foiEncontrado) {
			BD.set(indexBD, professor);
		}
	}
	
	public void remover(int id) {
		int indexBD = getIndexProfessor(id);
		boolean foiEncontrado = indexBD > 0;
		
		if(foiEncontrado) {
			BD.remove(indexBD);
		}
	}

	private int getIndexProfessor(int id) {
		int indexBD = -1;
		
		for (int i = 0; i < BD.size(); i++) {
			Professor modelTmp = BD.get(i);
			
			if(modelTmp.getId() == id) {
				indexBD = i;
			}
		}
		return indexBD;
	}

}
