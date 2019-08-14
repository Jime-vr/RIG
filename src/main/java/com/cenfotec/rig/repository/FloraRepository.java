package com.cenfotec.rig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.rig.models.Flora;

@Repository
public interface FloraRepository extends JpaRepository<Flora, Integer> {
 

}