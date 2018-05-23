package br.com.escola.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String email;

	private String telefone;

	@ManyToMany
	@Column(name = "idCurso")
	private List<Curso> cursos = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;
	
	@Transient
	private int idade;
	
	private float nota;
	
	@Column(name = "ismatriculado")
	private boolean isMatriculado;
	
	@Transient
	public int getIdInt() {
		return getId() == null ? 0 : getId();
	}
	
	@PostLoad
	private void carregaIdade() {
        Calendar hoje = Calendar.getInstance();
        int ajusteParaSaberSeJaFezAniversario = 0;
        if ( hoje.get(Calendar.DAY_OF_YEAR) - dataNascimento.get(Calendar.DAY_OF_YEAR) < 0) {
            ajusteParaSaberSeJaFezAniversario = -1;
        }
        idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR) + ajusteParaSaberSeJaFezAniversario;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void adicionarCurso(Curso curso) {
		cursos.add(curso);
	}

	public int getIdade() {
		return idade;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public boolean isMatriculado() {
		return isMatriculado;
	}

	public void setMatriculado(boolean isMatriculado) {
		this.isMatriculado = isMatriculado;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
