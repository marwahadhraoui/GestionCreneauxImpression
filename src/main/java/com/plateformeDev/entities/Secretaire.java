package com.plateformeDev.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("Secretaire")
public class Secretaire extends User {
	@JsonIgnore
	@OneToMany(mappedBy = "secretaire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Creneaux> creneaux;

	public Secretaire() {}
	
	public Secretaire(String role, String nom, String prenom, String email, String mdp) {
		super(role, nom, prenom, email, mdp);
	}
	
	public List<Creneaux> getCreneaux() {
		return creneaux;
	}

	public void setCreneaux(List<Creneaux> creneaux) {
		this.creneaux = creneaux;
	}

	@Override
	public String toString() {
		return "Secretaire [creneaux=" + creneaux + "]";
	}
	
}
