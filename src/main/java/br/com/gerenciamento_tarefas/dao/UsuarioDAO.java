package br.com.gerenciamento_tarefas.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.gerenciamento_tarefas.entities.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Long>{

	public Optional<Usuario> findByEmail(String email);
}
