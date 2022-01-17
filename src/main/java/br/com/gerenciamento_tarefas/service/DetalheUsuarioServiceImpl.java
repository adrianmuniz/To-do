package br.com.gerenciamento_tarefas.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.gerenciamento_tarefas.dao.UsuarioDAO;
import br.com.gerenciamento_tarefas.data.DetalheUsuarioData;
import br.com.gerenciamento_tarefas.entities.Usuario;

@Component
public class DetalheUsuarioServiceImpl implements  UserDetailsService{

	private final UsuarioDAO dao; 
	
	public DetalheUsuarioServiceImpl(UsuarioDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = dao.findByEmail(username);
		if(usuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
		}
		
		return new DetalheUsuarioData(usuario);
	}
	
	
	
}
