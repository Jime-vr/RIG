package com.cenfotec.rig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.rig.models.PoliticalDivision;

@Repository
public interface PoliticalDivisionRepository extends JpaRepository<PoliticalDivision, Integer> {
 

}
