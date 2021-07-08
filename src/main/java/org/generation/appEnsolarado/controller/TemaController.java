package org.generation.appEnsolarado.controller;

import java.util.List;

import org.generation.appEnsolarado.model.Tema;
import org.generation.appEnsolarado.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nometema/{nomeTema}")
	public ResponseEntity<List<Tema>> GetByNomeTema(@PathVariable String nomeTema){
		return ResponseEntity.ok(repository.findAllByNomeTemaContainingIgnoreCase(nomeTema));
	}
	
	@PostMapping
	public ResponseEntity<Tema> Post (@RequestBody Tema nomeTema){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(nomeTema));
	}
	
	@PutMapping
	public ResponseEntity<Tema> Put (@RequestBody Tema nomeTema){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(nomeTema));
	}
	
	@DeleteMapping("/{id}")
	public void DeleteById (@PathVariable long id) {
		repository.deleteById(id);
	}
}
