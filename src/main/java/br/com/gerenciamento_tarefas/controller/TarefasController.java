package br.com.gerenciamento_tarefas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/tarefas")
	public ResponseEntity<List<Tarefas>> todasTarefas() {
		List<Tarefas> tarefas = (List<Tarefas>) dao.findAll();
		
		if(tarefas.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(tarefas);
	}
	
	@GetMapping("/tarefas/{cod}")
	public ResponseEntity<Tarefas> tarefaPorId(@PathVariable long cod){
		
		Tarefas tarefa = dao.findById(cod).orElse(null);
		if(tarefa==null) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(tarefa);
	}
	
	@DeleteMapping("/tarefas/{id}")
	public ResponseEntity<Tarefas> deleteTarefa(@PathVariable long id){
		try {
			Tarefas tarefa = dao.findById(id).orElse(null);
			if(tarefa==null) {
				return ResponseEntity.status(404).build();
			}
			else {
				dao.delete(tarefa);
				
				return ResponseEntity.ok(tarefa);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}
}
