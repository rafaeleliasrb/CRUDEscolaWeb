package br.com.escola.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.escola.model.domain.Aluno;
import br.com.escola.model.domain.Curso;
import br.com.escola.model.service.AlunoService;
import br.com.escola.model.service.CursoService;

@ViewScoped
@ManagedBean
public class CadastrarAlunoBean {

	private AlunoService alunoService = new AlunoService();
	private CursoService cursoService = new CursoService();
	
	private Aluno aluno = new Aluno();
	private Integer idCurso;
	
	public void salvar() {
		if(aluno.getCursos().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage("Aluno deve ter pelo menos um Curso"));
            return;
		}
		alunoService.salvar(aluno);
		aluno = new Aluno();
	}

	public void adicionarCurso() {
		Curso curso = cursoService.buscar(idCurso);
		aluno.adicionarCurso(curso);
	}
	
	public String cadastrarNovoCurso() {
		System.out.println("Indo para o cadastro de Curso");
		return "form_curso?faces-redirect=true";
	}
	
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public List<Curso> getCursos() {
		return cursoService.listar();
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}
	
	public List<Aluno> getAlunos() {
		return alunoService.listar();
	}
}
