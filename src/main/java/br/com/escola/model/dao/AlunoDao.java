package br.com.escola.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.escola.model.domain.Aluno;
import br.com.escola.model.repository.ConnectionFactory;

public class AlunoDao {

	private EntityManager em;

	public AlunoDao() {
		this.em = ConnectionFactory.getInstance().getEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> listar() {		
		return this.em
		        .createQuery("select t from Aluno as t")
		        .getResultList();
	}
	
	public Aluno buscar(int idParam) {
		return this.em.find(Aluno.class, idParam);
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> buscar(String nomeParam) {
		return this.em
		        .createQuery("select t from Aluno as t where nome like '%" + nomeParam + "%'")
		        .getResultList();
	}
	
	public void inserir(Aluno aluno) {
		this.em.getTransaction().begin();        
		this.em.persist(aluno);
		this.em.getTransaction().commit();
	}
	
	public void alterar(Aluno aluno) {
		this.em.getTransaction().begin();        
		this.em.merge(aluno);
		this.em.getTransaction().commit();
	}
	
	public void remover(int id) throws Exception {
		Aluno aluno = buscar(id);
		
		if(aluno != null) {
			this.em.getTransaction().begin();        
			this.em.remove(aluno);
			this.em.getTransaction().commit();
		} else {
			throw new Exception("O aluno de ID = " + id + " não existe no banco");
		}
	}

}
