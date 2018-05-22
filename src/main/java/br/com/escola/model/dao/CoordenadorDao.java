package br.com.escola.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.escola.model.domain.Coordenador;

public class CoordenadorDao {
	
	private List<Coordenador> BD = new ArrayList<>();
	
	
	public List<Coordenador> listar() {
		return BD;
	}
	
	public Coordenador buscar(int id) {
		for (Coordenador coordenador : BD) {
			if(coordenador.getId() == id) {
				return coordenador;
			}
		}
		return null;
	}
	
	public Coordenador buscar(String nome) {
		for (Coordenador coordenador : BD) {
			if(coordenador.getNome() == nome) {
				return coordenador;
			}
		}
		return null;
	}
	
	public void inserir(Coordenador coordenador) {
		coordenador.setId(BD.size()+1);
		BD.add(coordenador);
	}
	
	public void alterar(Coordenador coordenador) {
		int indexBD = getIndexCoordenador(coordenador.getId());
		boolean foiEncontrado = indexBD > 0;
		
		if(foiEncontrado) {
			BD.set(indexBD, coordenador);
		}
	}
	
	public void remover(int id) {
		int indexBD = getIndexCoordenador(id);
		boolean foiEncontrado = indexBD > 0;
		
		if(foiEncontrado) {
			BD.remove(indexBD);
		}
	}

	private int getIndexCoordenador(int id) {
		int indexBD = -1;
		
		for (int i = 0; i < BD.size(); i++) {
			Coordenador modelTmp = BD.get(i);
			
			if(modelTmp.getId() == id) {
				indexBD = i;
			}
		}
		return indexBD;
	}

}
