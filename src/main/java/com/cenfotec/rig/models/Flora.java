package com.cenfotec.rig.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Flora {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_flora;
	private int id_country;
	private String name;
}