package br.com.escola.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.escola.model.dao.CursoDao;
import br.com.escola.model.domain.Aluno;
import br.com.escola.model.domain.Curso;
import br.com.escola.model.service.AlunoService;

@ViewScoped
@ManagedBean
public class FormAlunoBean {

	private AlunoService alunoService = new AlunoService();
	private CursoDao cursoDao = new CursoDao();
	
	private Aluno aluno = new Aluno();
	private Integer cursoId;
	
	public void gravar() {
		System.out.println("Gravando aluno...");
		if(aluno.getCursos().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("É necessário cadstrar um curso"));
			return;
		}
		alunoService.salvar(aluno);
		
		aluno = new Aluno();
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Cadstrado com sucesso"));
	}

	public void adicionarCurso() {
		Curso curso = cursoDao.buscar(cursoId);
		aluno.adicionarCurso(curso);
	}
	
	public String cadastrarNovoCurso() {
		System.out.println("Indo para o cadastro de Curso");
		return "form_curso";
	}
	
	public List<Aluno> getAlunos() {
		return alunoService.listar();
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
