package com.plateformeDev.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String typeImpression;
	private String niveau,specialite,matiere;
	private int nbrPage;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "creneaux_id")
    private Creneaux creneaux;
	
	public Reservation() {
		
	}

	public Reservation(String typeImpression, String niveau, String specialite, String matiere, int nbrPage) {
		super();
		this.typeImpression = typeImpression;
		this.niveau = niveau;
		this.specialite = specialite;
		this.matiere = matiere;
		this.nbrPage = nbrPage;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeImpression() {
		return typeImpression;
	}
	public void setTypeImpression(String typeImpression) {
		this.typeImpression = typeImpression;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public String getMatiere() {
		return matiere;
	}
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	public int getNbrPage() {
		return nbrPage;
	}
	public void setNbrPage(int nbrPage) {
		this.nbrPage = nbrPage;
	}

	
	public Creneaux getCreneaux() {
		return creneaux;
	}

	public void setCreneaux(Creneaux creneaux) {
		this.creneaux = creneaux;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", typeImpression=" + typeImpression + ", niveau=" + niveau + ", specialite="
				+ specialite + ", matiere=" + matiere + ", nbrPage=" + nbrPage + ", creneaux=" + creneaux + "]";
	}
	
	
	
	
}
