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
public class PoliticalDivision {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_region;
	private int id_country;
	private String region_name;
}
