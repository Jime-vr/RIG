package com.cenfotec.rig.controllers;

import java.util.ArrayList;
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

import com.cenfotec.rig.models.BiologicDivision;

import com.cenfotec.rig.repository.BiologicDivisionRepository;

@RestController
@RequestMapping({"/biologic"})
public class BiologicDivisionController {

	BiologicDivisionRepository repository;

	public BiologicDivisionController(BiologicDivisionRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<BiologicDivision> findById(@PathVariable int id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = "/name/{name}")
	public List<BiologicDivision> biologicalName(@PathVariable String name) {

		List<BiologicDivision> bioBD = null;
		List<BiologicDivision> finalList = new ArrayList<BiologicDivision>();

		bioBD = repository.findAll();
		
		String nameL = name.toLowerCase();

		for (BiologicDivision bd : bioBD) {
			if (bd.getBregion_name().contains(name) || bd.getBregion_name().contains(nameL) ) {
				finalList.add(bd);
			}
		}
		return finalList;
	}
	
	@GetMapping(path = "/country/{id}")
	public List<BiologicDivision> politicalCountry(@PathVariable Integer id) {

		List<BiologicDivision> bioBD = null;
		List<BiologicDivision> finalList = new ArrayList<BiologicDivision>();

		bioBD = repository.findAll();

		for (BiologicDivision bd : bioBD) {
			if (bd.getId_country() == id) {
				finalList.add(bd);
			}
		}
		return finalList;
	}
	
	@PostMapping
	public BiologicDivision create(@RequestBody BiologicDivision biologic) {
		return repository.save(biologic);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<BiologicDivision> update(@PathVariable("id") int id, @RequestBody BiologicDivision biologic) {
		return repository.findById(id).map(record -> {
			record.setId_biologic(biologic.getId_biologic());
			record.setId_country(biologic.getId_country());
			record.setId_political_division(biologic.getId_political_division());
			record.setBregion_name(biologic.getBregion_name());
			BiologicDivision updated = repository.save(record);
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
