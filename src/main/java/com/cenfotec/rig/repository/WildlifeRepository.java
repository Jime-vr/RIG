package com.cenfotec.rig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.rig.models.Wildlife;

@Repository
public interface WildlifeRepository extends JpaRepository<Wildlife, Integer> {
 

}