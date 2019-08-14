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
public class BiologicDivision {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_biologic;
	private int id_country;
	private int id_political_division;
	private String bregion_name;
}
