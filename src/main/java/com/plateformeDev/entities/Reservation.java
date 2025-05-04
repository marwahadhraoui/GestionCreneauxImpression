package com.plateformeDev.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String typeImpression;
	private String niveau,specialite,matiere;
	private int nbrPage;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "creneaux_id")
	@JsonBackReference 
    private Creneaux creneaux; 
	
	@ManyToOne
	@JoinColumn(name = "enseignant_id")
	private Enseignant enseignant; 
	
	 @ManyToOne
    @JoinColumn(name = "examen_id")
    private Examen examen;

	public Reservation(String typeImpression, String niveau, String specialite, String matiere, int nbrPage) {
		super();
		this.typeImpression = typeImpression;
		this.niveau = niveau;
		this.specialite = specialite;
		this.matiere = matiere;
		this.nbrPage = nbrPage;
	}
	
	 

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", typeImpression=" + typeImpression + ", niveau=" + niveau + ", specialite="
				+ specialite + ", matiere=" + matiere + ", nbrPage=" + nbrPage + ", creneaux=" + creneaux + "]";
	}
	
	
	
	
}
