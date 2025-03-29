package com.plateformeDev.entities;

import java.time.LocalDate;
import java.time.LocalTime;

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
	private LocalTime heure;
	private String statut;
	
	@ManyToOne
	@JoinColumn(name = "secretaire_id")
	private Secretaire secretaire;

	@OneToOne(mappedBy = "creneaux", cascade = CascadeType.ALL, orphanRemoval = true)
	private Reservation reservation;
	
	public Creneaux() {
	}

	public Creneaux(LocalDate date, LocalTime heure, String statut) {
		this.date = date;
		this.heure = heure;
		this.statut = statut;
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
	public LocalTime getHeure() {
		return heure;
	}
	public void setHeure(LocalTime heure) {
		this.heure = heure;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	

	public Secretaire getSecretaire() {
		return secretaire;
	}

	public void setSecretaire(Secretaire secretaire) {
		this.secretaire = secretaire;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return "Creneaux [id=" + id + ", date=" + date + ", heure=" + heure + ", statut=" + statut + ", secretaire="
				+ secretaire + ", reservation=" + reservation + "]";
	}

	
	
	
}
