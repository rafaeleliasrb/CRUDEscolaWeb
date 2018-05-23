package br.com.escola.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import br.com.escola.model.dao.CursoDao;
import br.com.escola.model.domain.Aluno;
import br.com.escola.model.domain.Curso;
import br.com.escola.model.service.AlunoService;

@ManagedBean
public class FormAlunoBean {

	private AlunoService alunoService = new AlunoService();
	private CursoDao cursoDao = new CursoDao();
	
	private Aluno aluno = new Aluno();
	private Integer cursoId;
	
	public void gravar() {
		System.out.println("Gravando aluno...");
		alunoService.salvar(aluno);
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public List<Curso> getCursos() {
		return cursoDao.listar();
	}

	public Integer getCursoId() {
		return cursoId;
	}

	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}
}
