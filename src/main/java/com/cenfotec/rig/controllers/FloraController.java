package com.cenfotec.rig.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.rig.models.Flora;
import com.cenfotec.rig.repository.FloraRepository;

@RestController
@RequestMapping({"/flora"})
public class FloraController {

	FloraRepository repository;

	public FloraController(FloraRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Flora> findById(@PathVariable int id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Flora create(@RequestBody Flora flora) {
		return repository.save(flora);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Flora> update(@PathVariable("id") int id, @RequestBody Flora flora) {
		return repository.findById(id).map(record -> {
			record.setId_flora(flora.getId_flora());
			record.setId_country(flora.getId_country());
			record.setName(flora.getName());
			Flora updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
}
