package com.plateformeDev.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
			/*boolean isSecretaire = user.getRole().equalsIgnoreCase("SECRETAIRE");

			if (!isSecretaire) {
				throw new RuntimeException("L'utilisateur avec l'ID " + user.getId() + " n'est pas un secrétaire.");
			}*/

			// Associer l'utilisateur en tant que secrétaire au créneau
			c.setSecretaire(user);
		}
		// Si le créneau est annulé, on peut l'utiliser
		if ("annule".equalsIgnoreCase(c.getStatut())) {
			c.setStatut("RESERVE"); // Mettre à jour le statut
			creneauxRepo.save(c);
		} else {

			// Vérifier si le créneau est déjà réservé dans la même période
			boolean exists = creneauxRepo.existsByDateAndTimeOverlap(c.getDate(), c.getHeureDebut(), c.getHeureFin());

			if (exists) {
				throw new RuntimeException("This time slot is already reserved.");
			}
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
		 List<Creneaux> cren = creneauxRepo.findAll();
	        
	        return cren.stream()
	                .sorted(Comparator.comparing(Creneaux::getDate))
	                .collect(Collectors.toList());
		 
	}

	@Override
	public Creneaux getCreneau(int id) {
		return creneauxRepo.findById(id).get();

	}
	
	@Override
	@Scheduled(fixedRate = 60000) // Runs every 60 seconds
	public void updateCreneauxStatus() {
		 LocalDateTime now = LocalDateTime.now();
		 LocalDate nowDate = now.toLocalDate();   // Extract the date
		 LocalTime nowTime = now.toLocalTime();   // Extract the time

		    List<Creneaux> creneauxList = creneauxRepo.findAllByStatutAndDateTimeBefore("RESERVE", nowDate, nowTime);
	    
	    for (Creneaux creneaux : creneauxList) {
	            creneaux.setStatut("TERMINE");
	            creneauxRepo.save(creneaux);
	        
	    }
	}

	@Override
	public List<Creneaux> getCreneauxByUser(User secretaire) {
		return creneauxRepo.findBySecretaire(secretaire);
	}
}
