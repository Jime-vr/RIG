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

import com.cenfotec.rig.models.Wildlife;
import com.cenfotec.rig.repository.WildlifeRepository;

@RestController
@RequestMapping({"/wildlife"})
public class WildlifeController {

	WildlifeRepository repository;

	public WildlifeController(WildlifeRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Wildlife> findById(@PathVariable int id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Wildlife create(@RequestBody Wildlife wildlife) {
		return repository.save(wildlife);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Wildlife> update(@PathVariable("id") int id, @RequestBody Wildlife wildlife) {
		return repository.findById(id).map(record -> {
			record.setId_wildlife(wildlife.getId_wildlife());
			record.setId_country(wildlife.getId_country());
			record.setMain_name(wildlife.getMain_name());
			record.setPopular_name(wildlife.getPopular_name());
			record.setCommon_name(wildlife.getCommon_name());
			record.setPopulation(wildlife.getPopulation());
			record.setConservation(wildlife.getConservation());
			Wildlife updated = repository.save(record);
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

