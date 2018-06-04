package br.com.escola.model.service;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.escola.model.dao.AlunoDao;
import br.com.escola.model.domain.Aluno;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoDao dao;
	
	@Transactional
	public void salvar(Aluno aluno) {
		if(aluno != null) {
			if(aluno.getIdInt() > 0) {
				dao.alterar(aluno);
			} else {
				dao.inserir(aluno);
			}
		}
	}
	
	public List<Aluno> listar() {
		return this.dao.listar();
	}

	public Aluno buscar(int id) {
		return this.dao.buscar(id);
	}
	
	public Aluno buscarComCursos(int id) {
		return this.dao.buscarComCursos(id);
	}
	
	public List<Aluno> buscar(String nome) {
		return this.dao.buscar(nome);
	}
	
	public void remover(int id) throws Exception {
		this.dao.remover(id);
	}

	public void imprimir(List<Aluno> list) {
		System.out.println("-- Alunos:");
		for (Aluno a : list) {
			System.out.println(a);
		}
	}

	public List<Aluno> listarTodosPaginado(int inicio, int quantidade, String campoOrdenacao, 
			SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		return dao.listarPaginado(inicio, quantidade, campoOrdenacao, sentidoOrdenacao, filtros);
	}
	
	public Integer contarTodosPaginado(Map<String, Object> filtros) {
		return dao.contarTodosPaginado(filtros);
	}
}
