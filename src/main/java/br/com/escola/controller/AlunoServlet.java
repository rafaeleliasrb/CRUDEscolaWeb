package br.com.escola.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.escola.model.domain.Aluno;
import br.com.escola.model.service.AlunoService;
import br.com.escola.model.utils.Utils;

@WebServlet("/AlunoServlet")
public class AlunoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static String FORM = "/form_aluno.jsp";
    private static String LIST = "/listar_aluno.jsp";

	private AlunoService alunoService;
       
    public AlunoServlet() {
        super();
        this.alunoService = new AlunoService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
	    String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("remover")){
            int alunoId = Integer.parseInt(request.getParameter("id"));
            
            try {
				this.alunoService.remover(alunoId);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
            forward = LIST;
			request.setAttribute("alunoList", this.alunoService.listar());
            
        } else if (action.equalsIgnoreCase("editar")){
            forward = FORM;
            int alunoId = Integer.parseInt(request.getParameter("id"));
            Aluno aluno = this.alunoService.buscar(alunoId);
            request.setAttribute("aluno", aluno);
            
        } else if (action.equalsIgnoreCase("buscar")){
        	String alunoNome = request.getParameter("nome_search");
        	forward = LIST;
			request.setAttribute("alunoList", this.alunoService.buscar(alunoNome));
            
        } else if (action.equalsIgnoreCase("listar")){
            forward = LIST;
			request.setAttribute("alunoList", this.alunoService.listar());
            
        } else {
        	request.setAttribute("aluno", new Aluno());
            forward = FORM;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Utils.checkValues(request.getParameterNames(), request);
			
			Aluno aluno = new Aluno();
			
			aluno.setNome(request.getParameter("nome"));
			aluno.setEmail(request.getParameter("email"));
			aluno.setTelefone(request.getParameter("telefone"));
			//aluno.setCurso(request.getParameter("curso"));
			aluno.setIdade(Integer.parseInt(request.getParameter("idade")));
			aluno.setMatriculado(Boolean.parseBoolean(request.getParameter("matriculado")));
			aluno.setNota(Float.parseFloat(request.getParameter("nota")));
			
			String id = request.getParameter("id");
			
			if (id != null && !id.isEmpty()) {
				int idInt = Integer.parseInt(id);
				if(idInt > 0) {
					aluno.setId(idInt);
				}
			}
			
			this.alunoService.salvar(aluno);
			
			// Redireciona para a tela listar
			response.sendRedirect("AlunoServlet?action=listar");

		} catch (Exception e) {
			e.printStackTrace();
			
			String id = request.getParameter("id");
			
			Aluno aluno = null;
			
			if (id != null && !id.isEmpty()) {
				aluno = this.alunoService.buscar(Integer.parseInt(id));
			} else {
				aluno = new Aluno();
			}
            request.setAttribute("aluno", aluno);
            request.setAttribute("erro", "Erro: " + e.getMessage());
			
			RequestDispatcher view = request.getRequestDispatcher(FORM);
	        view.forward(request, response);
		}
        
	}

}
