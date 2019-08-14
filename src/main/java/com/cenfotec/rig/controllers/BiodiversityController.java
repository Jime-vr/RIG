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

import com.cenfotec.rig.models.Biodiversity;
import com.cenfotec.rig.repository.BiodiversityRepository;

@RestController
@RequestMapping({ "/biodiversity" })
public class BiodiversityController {

	BiodiversityRepository repository;

	public BiodiversityController(BiodiversityRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public List findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Biodiversity> findById(@PathVariable int id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = { "/id" })
	public List<Biodiversity> bioCountryList(@PathVariable Integer id) {

		List<Biodiversity> bioBD = null;
		List<Biodiversity> finalList = new ArrayList<>();

		bioBD = repository.findAll();

		for (Biodiversity bd : bioBD) {
			if (bd.getId_country() == id) {
				finalList.add(bd);
			}
		}
		return finalList;
	}

	@GetMapping(path = "/{id}/{type}")
	public List<Biodiversity> animalCountry(@PathVariable Integer id, @PathVariable String type) {

		List<Biodiversity> bioBD = null;
		List<Biodiversity> finalList = new ArrayList<Biodiversity>();

		bioBD = repository.findAll();

		for (Biodiversity bd : bioBD) {
			if (bd.getId_country() == id && bd.getBio_type().contains(type)) {
				finalList.add(bd);
			}
		}
		return finalList;
	}

	@GetMapping(path = "/name/{name}")
	public List<Biodiversity> animalName(@PathVariable String name) {

		List<Biodiversity> bioBD = null;
		List<Biodiversity> finalList = new ArrayList<Biodiversity>();

		bioBD = repository.findAll();
		
		String nameL = name.toLowerCase();

		for (Biodiversity bd : bioBD) {
			if (bd.getMain_name().contains(name) || bd.getCommon_name().contains(name)
					|| bd.getPopular_name().contains(name) || bd.getMain_name().contains(nameL) || bd.getCommon_name().contains(nameL)
					|| bd.getPopular_name().contains(nameL)) {
				finalList.add(bd);
			}
		}
		return finalList;
	}

	@PostMapping
	public Biodiversity create(@RequestBody Biodiversity bio) {
		return repository.save(bio);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Biodiversity> update(@PathVariable("id") int id, @RequestBody Biodiversity bio) {
		return repository.findById(id).map(record -> {
			record.setId_biodiversity(bio.getId_biodiversity());
			record.setId_country(bio.getId_country());
			record.setMain_name(bio.getMain_name());
			record.setPopular_name(bio.getPopular_name());
			record.setCommon_name(bio.getCommon_name());
			record.setPopulation(bio.getPopulation());
			record.setConservation(bio.getConservation());
			Biodiversity updated = repository.save(record);
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
