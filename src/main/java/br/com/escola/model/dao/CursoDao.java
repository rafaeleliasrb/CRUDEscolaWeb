package br.com.escola.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.escola.model.domain.Curso;
import br.com.escola.model.repository.ConnectionFactory;

public class CursoDao {

	private EntityManager em;

	public CursoDao() {
		this.em = ConnectionFactory.getInstance().getEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<Curso> listar() {		
		return this.em
		        .createQuery("select t from Curso as t")
		        .getResultList();
	}
}
