package br.com.gerenciamento_tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gerenciamento_tarefas.dao.UsuarioDAO;
import br.com.gerenciamento_tarefas.entities.Usuario;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;
	
	@PostMapping("/criarUsuario")
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
		try {
			dao.save(usuario);
			
			return ResponseEntity.ok(usuario);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> login (@RequestBody Usuario usuario){
		
		Usuario logar = dao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
		
		if(logar == null) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(logar);
	}
}
