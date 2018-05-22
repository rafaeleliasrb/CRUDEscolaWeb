package br.com.escola.model.service;

import java.util.List;

import br.com.escola.model.dao.AlunoDao;
import br.com.escola.model.domain.Aluno;

public class AlunoService {
	
	private AlunoDao dao = new AlunoDao();
	
	public AlunoService() {
//		popularBanco();
	}
	
//	private void popularBanco() {
//		Aluno aluno = new Aluno();
//		aluno.setId(0);
//		aluno.setNome("Yuri");
//		aluno.setEmail("yuri@gmail.com");
//		aluno.setTelefone("85999999999");
//		aluno.setCurso("Java para Web");
//		aluno.setIdade(28);
//		aluno.setNota(9.5f);
//		aluno.setMatriculado(true);
//		
//		salvar(aluno);
//	}
	
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

}
