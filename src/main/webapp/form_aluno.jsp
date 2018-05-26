<%@page import="java.util.List"%>
<%@page import="br.com.escola.model.domain.Aluno"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>ESCOLA</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="index.jsp">
	  <h2>ESCOLA</h2>	
	  </a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	</nav>


	<div class="container" style="margin-top: 40px;">
		
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="index.jsp">Início</a></li>
		    <li class="breadcrumb-item"><a href="AlunoServlet?action=listar">Alunos</a></li>
		    <li class="breadcrumb-item active" aria-current="page">Formulário</li>
		  </ol>
		</nav>
		
		<% 
		Aluno aluno = (Aluno) request.getAttribute("aluno");
		String erro = (String) request.getAttribute("erro");
		
		if(erro != null && !erro.equals("")){
		%>
		<div class="alert alert-danger" role="alert">
		  <%= erro %>
		</div>
		<% 
		}
		%>
	
		<form method="POST" action='AlunoServlet' name="frmAddAluno">
	        <div class="form-group">
			    <label for="id">ID</label>
			    <input type="text" class="form-control" id="id" name="id" placeholder="Insira seu nome" value="<%= (aluno != null && aluno.getIdInt() > 0) ? aluno.getIdInt() : 0 %>" readonly>
			</div>
	            
            <div class="form-row">
		        <div class="form-group col-7">
				    <label for="nome">Nome</label>
				    <input type="text" class="form-control" id="nome" name="nome" placeholder="Insira seu nome" value="<%= (aluno != null && aluno.getNome() != null) ? aluno.getNome() : "" %>" >
				</div>
			
		        <div class="form-group col-4">
				    <label for="telefone">Telefone</label>
				    <input type="text" class="form-control" id="telefone" name="telefone" placeholder="Insira seu telefone" value="<%= (aluno != null && aluno.getTelefone() != null) ? aluno.getTelefone() : "" %>" >
				</div>
			
		        <div class="form-group col-1">
				    <label for="idade">Idade</label>
				    <input type="text" class="form-control" id="idade" name="idade" value="<%= (aluno != null && aluno.getIdade() > 0) ? aluno.getIdade() : 0 %>" >
				</div>
			</div>
	            
            <div class="form-row">
		        <div class="form-group col-7">
				    <label for="email">Email</label>
				    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="Insira seu email" value="<%= (aluno != null && aluno.getEmail() != null) ? aluno.getEmail() : "" %>" >
				    <small id="emailHelp" class="form-text text-muted">Nunca iremos compartilhar seu email com ninguém.</small>
				</div>
				
		        <%-- <div class="form-group col-4">
				    <label for="curso">Curso</label>
				    <input type="text" class="form-control" id="curso" name="curso" placeholder="Insira seu curso" value="<%= (aluno != null && aluno.getCurso() != null) ? aluno.getCurso() : "" %>" >
				</div> --%>
			
		        <div class="form-group col-1">
				    <label for="nota">Nota</label>
				    <input type="text" class="form-control" id="nota" name="nota" value="<%= (aluno != null && aluno.getNota() >= 0) ? aluno.getNota() : 0 %>" >
				</div>
			</div>
			

			<div class="form-group">			
			    <label for="matriculado">Matriculado</label>
			    
				<div class="form-check">
				  <input class="form-check-input" type="radio" name="matriculado" id="matriculadoSim" value="true"
				  	<% if(aluno == null || (aluno != null && aluno.isMatriculado())){ %>            
		            checked
		            <% } %>
				  >
				  <label class="form-check-label" for="matriculadoSim">Sim</label>
				</div>				  
				
				<div class="form-check">
				  <input class="form-check-input" type="radio" name="matriculado" id="matriculadoNao" value="false"
				  	<% if(aluno != null && !aluno.isMatriculado()){ %>            
		            checked
		            <% } %>
				  >
				  <label class="form-check-label" for="matriculadoNao">Não</label>
				</div>
			</div>
	            
	        <input type="submit" value="Salvar" class="btn btn-success" />
	    </form>
    </div>
</body>
</html>
