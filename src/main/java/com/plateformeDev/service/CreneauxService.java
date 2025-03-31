package com.plateformeDev.service;

import java.util.List;

import com.plateformeDev.entities.Creneaux;

public interface CreneauxService {
	
	Creneaux saveCreneau(Creneaux c);
	Creneaux updateCreneau(Creneaux c);
	void deleteCreneaux(Creneaux c);
	void deleteCreneauxById(int id);
	Creneaux getCreneau(int id);
	List<Creneaux> getAllCreneaux();

}
