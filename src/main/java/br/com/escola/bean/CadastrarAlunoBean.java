package br.com.escola.bean;

import javax.faces.bean.ManagedBean;

import br.com.escola.model.domain.Aluno;
import br.com.escola.model.service.AlunoService;

@ManagedBean
public class CadastrarAlunoBean {

	private AlunoService alunoService = new AlunoService();
	
	private Aluno aluno = new Aluno();
	
	public void salvar() {
		System.out.println("Salvando aluno ...");
		alunoService.salvar(aluno);
		aluno = new Aluno();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
