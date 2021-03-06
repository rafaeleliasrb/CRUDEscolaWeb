package br.com.escola.model.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;

import br.com.escola.model.domain.Aluno;
import br.com.escola.model.domain.TipoSelect;

@Repository
public class AlunoDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Aluno> listar() {		
		return this.em
		        .createQuery("select t from Aluno as t")
		        .getResultList();
	}
	
	public Aluno buscar(int idParam) {
		return this.em.find(Aluno.class, idParam);
	}
	
	public Aluno buscarComCursos(int idParam) {
		return em
		        .createQuery("select t from Aluno as t join fecth t.cursos c where t.id = :id", Aluno.class)
		        .setParameter("id", idParam)
		        .getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> buscar(String nomeParam) {
		return this.em
		        .createQuery("select t from Aluno as t where nome like '%" + nomeParam + "%'")
		        .getResultList();
	}
	
	public void inserir(Aluno aluno) {
		this.em.persist(aluno);
	}
	
	public void alterar(Aluno aluno) {
		this.em.merge(aluno);
	}
	
	public void remover(int id) throws Exception {
		Aluno aluno = buscar(id);
		
		if(aluno != null) {
			this.em.remove(aluno);
		} else {
			throw new Exception("O aluno de ID = " + id + " não existe no banco");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> listarPaginado(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao,
			Map<String, Object> filtros) {
		
		return (List<Aluno>) queryListarPaginado(TipoSelect.OBJETO, campoOrdenacao, sentidoOrdenacao, filtros)
				.setFirstResult(inicio)
				.setMaxResults(quantidade)
				.getResultList();
	}

	public Integer contarTodosPaginado(Map<String, Object> filtros) {
		return ((Long) queryListarPaginado(TipoSelect.CONTADOR, null, null, filtros).getSingleResult()).intValue();
	}

	private Query queryListarPaginado(TipoSelect select, 
			String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		StringBuilder sql = selectFrom(select);
		where(filtros, sql);
		orderBy(select, campoOrdenacao, sentidoOrdenacao, sql);
		return criaQueryESetaParametros(filtros, sql);
	}

	private Query criaQueryESetaParametros(Map<String, Object> filtros, StringBuilder sql) {
		Query query = this.em.createQuery(sql.toString());
		for (Map.Entry<String,Object> entry : filtros.entrySet()) {
		    String filtro = entry.getKey();
		    Object valorFiltro = entry.getValue();
			if(valorFiltro instanceof String) {
				valorFiltro = "%" + valorFiltro + "%";
			}
			query.setParameter(filtro, valorFiltro);
		}
		return query;
	}

	private void orderBy(TipoSelect select, String campoOrdenacao, SortOrder sentidoOrdenacao, StringBuilder sql) {
		if(sentidoOrdenacao != null && campoOrdenacao != null && select.equals(TipoSelect.OBJETO)) {
			String sentidoOrdenacaoLowerCase = sentidoOrdenacao.name().toLowerCase();
			String order = sentidoOrdenacao.equals(SortOrder.ASCENDING)? 
					sentidoOrdenacaoLowerCase.substring(0, 3) : sentidoOrdenacaoLowerCase.substring(0, 4);
			sql.append(" order by t.").append(campoOrdenacao).append(" ").append(order);
		}
	}

	private void where(Map<String, Object> filtros, StringBuilder sql) {
		if(!filtros.isEmpty()) {
			sql.append("where ");
			for (Map.Entry<String,Object> entry : filtros.entrySet()) {
			    String filtro = entry.getKey();
			    Object valorFiltro = entry.getValue();
				String operador;
				if(valorFiltro instanceof String) {
					operador = " like ";
				}
				else {
					operador = " = ";
				}
				sql.append("t.").append(filtro).append(operador).append(" :").append(filtro).append(" and ");
			}
			sql.delete(sql.length()-4, sql.length()-1);
		}
	}

	private StringBuilder selectFrom(TipoSelect select) {
		StringBuilder sql = new StringBuilder("select ");
		if(select.equals(TipoSelect.OBJETO)) {
			sql.append("t ");
		}
		else {
			sql.append("count(t) ");
		}
		sql.append("from Aluno t ");
		return sql;
	}
}
