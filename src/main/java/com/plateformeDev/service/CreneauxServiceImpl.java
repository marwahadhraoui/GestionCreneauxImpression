package com.plateformeDev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plateformeDev.entities.Creneaux;
import com.plateformeDev.entities.User;
import com.plateformeDev.repos.CreneauxRepository;
import com.plateformeDev.repos.UserRepository;

@Service
public class CreneauxServiceImpl implements CreneauxService {

	@Autowired
	CreneauxRepository creneauxRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Creneaux saveCreneau(Creneaux c) {
		if (c.getSecretaire() != null && c.getSecretaire().getId() != 0) {
			// Récupérer l'utilisateur depuis la base
			User user = userRepo.findById(c.getSecretaire().getId())
					.orElseThrow(() -> new RuntimeException("User not found with id: " + c.getSecretaire().getId()));

			// Vérifier si l'utilisateur a bien le rôle "SECRETAIRE"
			boolean isSecretaire = user.getRole().equalsIgnoreCase("SECRETAIRE");

			if (!isSecretaire) {
				throw new RuntimeException("L'utilisateur avec l'ID " + user.getId() + " n'est pas un secrétaire.");
			}

			// Associer l'utilisateur en tant que secrétaire au créneau
			c.setSecretaire(user);
		}

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
