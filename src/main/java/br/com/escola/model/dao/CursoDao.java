package br.com.escola.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.escola.model.domain.Curso;

@Repository
public class CursoDao {

	@PersistenceContext(unitName="maindatabase")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Curso> listar() {		
		return em
		        .createQuery("select t from Curso as t")
		        .getResultList();
	}
	
	public Curso buscar(int idParam) {
		return this.em.find(Curso.class, idParam);
	}
	
	public void inserir(Curso curso) {
		this.em.getTransaction().begin();        
		this.em.persist(curso);
		this.em.getTransaction().commit();
	}
	
	public void alterar(Curso curso) {
		this.em.getTransaction().begin();        
		this.em.merge(curso);
		this.em.getTransaction().commit();
	}
}
