package br.com.gerenciamento_tarefas.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="Id")
	private long id;
	
	@Column(name="nome", nullable = true)
	private String nome;
	
	@Column(name="email", nullable = true)
	private String email;
	
	@Column(name="senha", nullable = true)
	private String senha;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Tarefas> tarefa;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Tarefas> getTarefa() {
		return tarefa;
	}

	public void setTarefa(List<Tarefas> tarefa) {
		this.tarefa = tarefa;
	}
	
	
}
