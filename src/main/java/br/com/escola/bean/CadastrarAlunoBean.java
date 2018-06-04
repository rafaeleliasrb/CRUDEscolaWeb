package br.com.escola.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.escola.model.dao.AlunoDao;
import br.com.escola.model.domain.Aluno;
import br.com.escola.model.domain.Curso;
import br.com.escola.model.service.AlunoService;
import br.com.escola.model.service.CursoService;

@Scope("view")
@Controller
public class CadastrarAlunoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private CursoService cursoService;
	
	private Aluno aluno = new Aluno();
	private Integer idCurso;
	private List<Aluno> alunos;
	private List<Curso> cursos;
	private LazyDataModel<Aluno> alunosLazy = new LazyDataModel<Aluno>() {

		private static final long serialVersionUID = 1L;

		@Override
		public List<Aluno> load(int first, int pageSize, String sortField, SortOrder sortOrder,
				Map<String, Object> filters) {
			setRowCount(alunoService.contarTodosPaginado(filters));
			return alunoService.listarTodosPaginado(first, pageSize, sortField, sortOrder, filters);
		}	
		
	};
	
	public void salvar() {
		if(aluno.getCursos().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage("Aluno deve ter pelo menos um Curso"));
            return;
		}
		alunoService.salvar(aluno);
		FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage("Aluno salvo com sucesso"));
		
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
		//this.aluno = alunoService.buscar(aluno.getId());
		//this.aluno.getCursos();
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
		if(cursos == null) {
			cursos = cursoService.listar();
		}
		return cursos;
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
	
	public LazyDataModel<Aluno> getAlunosLazy() {
		return alunosLazy;
	}
}
