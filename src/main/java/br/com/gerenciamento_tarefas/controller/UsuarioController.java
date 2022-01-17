package br.com.gerenciamento_tarefas.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gerenciamento_tarefas.dao.UsuarioDAO;
import br.com.gerenciamento_tarefas.entities.Usuario;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;
	private final PasswordEncoder encoder;
	
	public UsuarioController(UsuarioDAO dao, PasswordEncoder encoder) {
		this.dao = dao;
		this.encoder = encoder;
	}

	@PostMapping("/criarUsuario")
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
		try {
			usuario.setSenha(encoder.encode(usuario.getSenha()));
			dao.save(usuario);
			
			return ResponseEntity.ok(usuario);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}
	
	@GetMapping("/usuario/{cod}")
	public ResponseEntity<Usuario> usuarioPorId(@PathVariable long cod){
		
		Usuario user = dao.findById(cod).orElse(null);
		if(user==null) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/usuarios/listarTodos")
	public ResponseEntity<Usuario> todosUsuarios(@PathVariable long cod){
		
		Usuario user = (Usuario) dao.findAll();
		if(user==null) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(user);
	}
	
	
	@GetMapping("/usuarios/validarsenha")
	public ResponseEntity<Boolean> validarSenha(@RequestParam String email, @RequestParam String senha){
		
		
		Optional<Usuario> optUsuario = dao.findByEmail(email);
		if(optUsuario==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		
		Usuario usuario = optUsuario.get();
		boolean valid = encoder.matches(senha, usuario.getSenha());
		
		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(valid);
	}
	
}
