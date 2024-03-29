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

import com.cenfotec.rig.models.Country;
import com.cenfotec.rig.repository.CountryRepository;


@RestController
@RequestMapping({ "/country" })
public class CountryController {

	private CountryRepository repository;


	CountryController(CountryRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Country> findById(@PathVariable Integer id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	public Country create(@RequestBody Country country) {
		return repository.save(country);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Country> update(@PathVariable("id") Integer id, @RequestBody Country country) {
		return repository.findById(id).map(record -> {
			record.setId_country(country.getId_country());
			record.setName(country.getName());
			record.setContinent(country.getContinent());
			record.setLand_surface(country.getLand_surface());
			record.setMarine_surface(country.getMarine_surface());
			Country updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
}
