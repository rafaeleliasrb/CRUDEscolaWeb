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
	
	public Curso buscar(int idParam) {
		return this.em.find(Curso.class, idParam);
	}
	
	@SuppressWarnings("unchecked")
	public List<Curso> buscar(String nomeParam) {
		return this.em
		        .createQuery("select t from Curso as t where nome like '%" + nomeParam + "%'")
		        .getResultList();
	}
	
	public void inserir(Curso Curso) {
		this.em.getTransaction().begin();        
		this.em.persist(Curso);
		this.em.getTransaction().commit();
	}
	
	public void alterar(Curso Curso) {
		this.em.getTransaction().begin();        
		this.em.merge(Curso);
		this.em.getTransaction().commit();
	}
	
	public void remover(int id) throws Exception {
		Curso Curso = buscar(id);
		
		if(Curso != null) {
			this.em.getTransaction().begin();        
			this.em.remove(Curso);
			this.em.getTransaction().commit();
		} else {
			throw new Exception("O Curso de ID = " + id + " não existe no banco");
		}
	}

}
