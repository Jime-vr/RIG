package com.cenfotec.rig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.rig.models.Biodiversity;

@Repository
public interface BiodiversityRepository extends JpaRepository<Biodiversity, Integer> {
 

}