<%@page import="java.util.List"%>
<%@page import="br.com.escola.model.domain.Aluno"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
		    <li class="breadcrumb-item active" aria-current="page">Alunos</li>
		  </ol>
		</nav>
		
		<div class="float-right">		
			<p><a href="AlunoServlet?action=inserir" class="btn btn-info">+ Novo Aluno</a></p>
		</div>
			
		<form method="GET" action="AlunoServlet" >
			<div class="input-group mb-3">
				<input type="hidden" name="action" value="buscar">
		  		<input type="text" name="nome_search" class="form-control" placeholder="Insira aqui o nome do aluno a ser pesquisado..." aria-label="Recipient's username" aria-describedby="basic-addon2">
				<div class="input-group-append">
					<button class="btn btn-outline-secondary" type="submit">Pesquisar</button>
				</div>
			</div>
		</form>
		
		<div class="table-responsive">			
			<table class="table table-striped table-bordered table-hover">
				<caption>Lista de alunos</caption>
				<thead class="thead-dark">
				    <tr>
				      <th scope="col">#</th>
				      <th scope="col">Nome</th>
				      <th scope="col"></th>
				      <th scope="col"></th>
				    </tr>
				</thead>
				
				<tbody>
					<% 
					List<Aluno> alunoList = (List<Aluno>) request.getAttribute("alunoList"); 
					for (Aluno aluno : alunoList) {
					%>
					<tr>
						<td><%= aluno.getIdInt() %></td>
						<td><%= aluno.getNome() %></td>
						<td><a class="btn btn-outline-info btn-sm" href="AlunoServlet?action=editar&id=<%= aluno.getIdInt() %>">Editar</a></td>
						<td><a class="btn btn-outline-info btn-sm" href="AlunoServlet?action=remover&id=<%= aluno.getIdInt() %>">Remover</a></td>
					</tr>
					<% 
					} 
					%>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>
