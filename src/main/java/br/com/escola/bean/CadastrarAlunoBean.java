package br.com.escola.bean;

import javax.faces.bean.ManagedBean;

import br.com.escola.model.domain.Aluno;

@ManagedBean
public class CadastrarAlunoBean {

	private Aluno aluno = new Aluno();
	
	public void salvar() {
		System.out.println("Salvando aluno ...");
		System.out.println(aluno.toString());
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
