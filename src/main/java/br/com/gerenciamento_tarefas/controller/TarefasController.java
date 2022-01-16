package br.com.gerenciamento_tarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gerenciamento_tarefas.dao.TarefasDAO;
import br.com.gerenciamento_tarefas.entities.Tarefas;

@RestController
public class TarefasController {

	@Autowired
	private TarefasDAO dao;
	
	@PostMapping("/criarTarefa")
	public ResponseEntity<Tarefas> criarTarefa (@RequestBody Tarefas tarefa){
		try {
			dao.save(tarefa);
			
			return ResponseEntity.ok(tarefa);
		}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}
	
}
