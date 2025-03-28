package com.plateformeDev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.plateformeDev.entities.Creneaux;
import com.plateformeDev.repos.CreneauxRepository;

public class CreneauxServiceImpl implements CreneauxService {

	@Autowired
	CreneauxRepository creneauxRepo;
	
	@Override
	public Creneaux saveCreneau(Creneaux c) {
		return creneauxRepo.save(c);
	}

	@Override
	public Creneaux updateCreneau(Creneaux c) {
		return creneauxRepo.save(c);

	}

	@Override
	public void deleteCreneaux(Creneaux c) {
		 creneauxRepo.delete(c);
		
	}

	@Override
	public void deleteCreneauxById(int id) {
		 creneauxRepo.deleteById(id);
		
	}

	@Override
	public List<Creneaux> getAllCreneaux() {
		return creneauxRepo.findAll();
	}

	@Override
	public Creneaux getCreneau(int id) {
		return creneauxRepo.findById(id).get();

	}

}
