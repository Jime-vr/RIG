package com.cenfotec.rig.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Country {
	@Id
	private int id_country;
	private String name;
	private String continent;
	private String land_surface;
	private String marine_surface;
	
}
