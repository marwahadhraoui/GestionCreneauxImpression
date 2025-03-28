package com.plateformeDev.entities;

import jakarta.persistence.Entity;

@Entity
public class Secretaire extends User {
	public Secretaire() {}
	
	public Secretaire(String role, String nom, String prenom, String email, String mdp) {
		super(role, nom, prenom, email, mdp);
	}
	

}
