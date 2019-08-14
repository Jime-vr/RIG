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

import com.cenfotec.rig.models.PoliticalDivision;
import com.cenfotec.rig.repository.PoliticalDivisionRepository;

@RestController
@RequestMapping({"/political"})
public class PoliticalDivisionController {

	PoliticalDivisionRepository repository;

	public PoliticalDivisionController(PoliticalDivisionRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<PoliticalDivision> findById(@PathVariable int id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public PoliticalDivision create(@RequestBody PoliticalDivision political) {
		return repository.save(political);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PoliticalDivision> update(@PathVariable("id") int id, @RequestBody PoliticalDivision political) {
		return repository.findById(id).map(record -> {
			record.setId_region(political.getId_region());
			record.setId_country(political.getId_country());
			record.setRegion_name(political.getRegion_name());
			PoliticalDivision updated = repository.save(record);
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