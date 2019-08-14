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
public class Biodiversity {
	@Id
	private int id_biodiversity;
	private int id_country;
	private String bio_type;
	private String main_name;
	private String popular_name;
	private String common_name;
	private String population;
	private String conservation;
}
