package br.com.escola.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.escola.model.domain.Curso;
import br.com.escola.model.service.CursoService;

@ManagedBean
public class CadastrarCursoBean {

	private CursoService cursoService = new CursoService();
	
	private Curso curso = new Curso();
	
	public Curso getCurso() {
		return curso;
	}
	
	public String salvar() {
		cursoService.salvar(curso);
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
	    context.addMessage(null, new FacesMessage("Curso cadastrado com sucesso"));
		return "form_aluno?faces-redirect=true";
	}
}
