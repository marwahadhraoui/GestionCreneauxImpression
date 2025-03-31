package com.plateformeDev.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Creneaux {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate date;
	private LocalTime heureDebut;
	private LocalTime heureFin;
	private String statut;
	@ManyToOne
	@JoinColumn(name = "secretaire_id")
	private User  secretaire;
	@JsonManagedReference

	@OneToOne(mappedBy = "creneaux", cascade = CascadeType.ALL, orphanRemoval = true)
	 // pour éviter la référence circulaire
	private Reservation reservation;
	
	public Creneaux() {
	}

	public Creneaux(LocalDate date, LocalTime heureDebut, LocalTime heureFin, String statut, User secretaire,
			Reservation reservation) {
		super();
		this.date = date;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.statut = statut;
		this.secretaire = secretaire;
		this.reservation = reservation;
	}

	public int getId() {
		return id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	

	public User  getSecretaire() {
		return secretaire;
	}

	public void setSecretaire(User secretaire) {
		this.secretaire = secretaire;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
	public void setId(int id) {
		this.id = id;
	}


	public LocalTime getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}

	public LocalTime getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(LocalTime heureFin) {
		this.heureFin = heureFin;
	}

	@Override
	public String toString() {
		return "Creneaux [id=" + id + ", date=" + date + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin
				+ ", statut=" + statut + ", secretaire=" + secretaire + ", reservation=" + reservation + "]";
	}

	
	
	
	
}
