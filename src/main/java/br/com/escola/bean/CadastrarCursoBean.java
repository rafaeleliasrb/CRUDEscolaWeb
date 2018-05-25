package br.com.escola.bean;

import javax.faces.bean.ManagedBean;

import br.com.escola.model.domain.Curso;

@ManagedBean
public class CadastrarCursoBean {

	private Curso curso = new Curso();
	
	public Curso getCurso() {
		return curso;
	}
}
