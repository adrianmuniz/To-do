package br.com.gerenciamento_tarefas.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.gerenciamento_tarefas.entities.Tarefas;

public interface TarefasDAO extends CrudRepository<Tarefas, Long>{

	public List<Tarefas> findByStatusLike(String status);
}
