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
	private List<Aluno> alunos;
	
	public void salvar() {
		if(aluno.getCursos().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage("Aluno deve ter pelo menos um Curso"));
            return;
		}
		alunoService.salvar(aluno);
		
		alunos = alunoService.listar();
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
	
	public void remover(Aluno aluno) throws Exception {
		System.out.println("Removendo o aluno de id: " + aluno.getId());
		alunoService.remover(aluno.getId());
	}
	
	public void preparaAlteracao(Aluno aluno) {
		System.out.println("Carregando aluno de id: " + aluno.getId() + " para alterar");
		this.aluno = aluno;
	}
	
	public void removerCursoDoAluno(Curso curso) {
		System.out.println("Removendo o curso de id: " + curso.getId());
		aluno.removerCurso(curso);
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
		if(alunos == null) {
			alunos = alunoService.listar();
		}
		return alunos;
	}
}
