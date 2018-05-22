package br.com.escola.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.escola.model.domain.Coordenador;

public class CoordenadorDao {
	
	private List<Coordenador> database = new ArrayList<>();
	
	
	public List<Coordenador> listar() {
		return database;
	}
	
	public Coordenador buscar(int id) {
		for (Coordenador coordenador : database) {
			if(coordenador.getId() == id) {
				return coordenador;
			}
		}
		return null;
	}
	
	public Coordenador buscar(String nome) {
		for (Coordenador coordenador : database) {
			if(coordenador.getNome() == nome) {
				return coordenador;
			}
		}
		return null;
	}
	
	public void inserir(Coordenador coordenador) {
		coordenador.setId(database.size()+1);
		database.add(coordenador);
	}
	
	public void alterar(Coordenador coordenador) {
		int indexBD = getIndexCoordenador(coordenador.getId());
		boolean foiEncontrado = indexBD > 0;
		
		if(foiEncontrado) {
			database.set(indexBD, coordenador);
		}
	}
	
	public void remover(int id) {
		int indexBD = getIndexCoordenador(id);
		boolean foiEncontrado = indexBD > 0;
		
		if(foiEncontrado) {
			database.remove(indexBD);
		}
	}

	private int getIndexCoordenador(int id) {
		int indexBD = -1;
		
		for (int i = 0; i < database.size(); i++) {
			Coordenador modelTmp = database.get(i);
			
			if(modelTmp.getId() == id) {
				indexBD = i;
			}
		}
		return indexBD;
	}

}
