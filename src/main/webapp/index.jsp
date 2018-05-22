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

		<div class="jumbotron">
		  <h1 class="display-4">Bem vindo!</h1>
		  <p class="lead">Este sistema é um CRUD de um projeto de uma escola</p>
		  <hr class="my-4">
		  <p>Nele, contém as entidades: Aluno, Professor e Coordenador.</p>
		  
		  <p class="lead">
		    <a class="btn btn-warning btn-lg" href="AlunoServlet?action=listar" role="button">Alunos</a>
		    <a class="btn btn-info btn-lg" href="listar_professor.jsp" role="button">Professores</a>
		    <a class="btn btn-dark btn-lg" href="listar_coordenador.jsp" role="button">Coordenadores</a>
		  </p>
		</div>
		
	</div>

</body>
</html>