package br.com.gerenciamento_tarefas.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.gerenciamento_tarefas.entities.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Long>{

}
