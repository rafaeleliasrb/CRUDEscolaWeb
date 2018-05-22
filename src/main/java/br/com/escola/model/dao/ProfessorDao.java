package br.com.escola.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.escola.model.domain.Professor;

public class ProfessorDao {
	
	private List<Professor> database = new ArrayList<>();
	
	
	public List<Professor> listar() {
		return database;
	}
	
	public Professor buscar(int id) {
		for (Professor professor : database) {
			if(professor.getId() == id) {
				return professor;
			}
		}
		return null;
	}
	
	public Professor buscar(String nome) {
		for (Professor professor : database) {
			if(professor.getNome() == nome) {
				return professor;
			}
		}
		return null;
	}
	
	public void inserir(Professor professor) {
		professor.setId(database.size()+1);
		database.add(professor);
	}
	
	public void alterar(Professor professor) {
		int indexBD = getIndexProfessor(professor.getId());
		boolean foiEncontrado = indexBD > 0;
		
		if(foiEncontrado) {
			database.set(indexBD, professor);
		}
	}
	
	public void remover(int id) {
		int indexBD = getIndexProfessor(id);
		boolean foiEncontrado = indexBD > 0;
		
		if(foiEncontrado) {
			database.remove(indexBD);
		}
	}

	private int getIndexProfessor(int id) {
		int indexBD = -1;
		
		for (int i = 0; i < database.size(); i++) {
			Professor modelTmp = database.get(i);
			
			if(modelTmp.getId() == id) {
				indexBD = i;
			}
		}
		return indexBD;
	}

}
