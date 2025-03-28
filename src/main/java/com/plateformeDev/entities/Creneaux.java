package com.plateformeDev.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Creneaux {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate date;
	private LocalTime heure;
	private String statut;
	
	
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

	@Override
	public String toString() {
		return "Crenaux [id=" + id + ", date=" + date + ", heure=" + heure + ", statut=" + statut + "]";
	}
	
	
}
