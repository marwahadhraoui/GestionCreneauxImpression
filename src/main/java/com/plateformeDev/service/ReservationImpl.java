package com.plateformeDev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plateformeDev.entities.Creneaux;
import com.plateformeDev.entities.Reservation;
import com.plateformeDev.repos.CreneauxRepository;
import com.plateformeDev.repos.ReservationRepository;

@Service
public class ReservationImpl implements ReservationService {
	
	 @Autowired
	 private ReservationRepository reservationRepo;
	    
	 @Autowired
	 private CreneauxRepository creneauxRepo;

	@Override
	public Reservation saveReservation(Reservation r) {
        if (r.getCreneaux() != null && r.getCreneaux().getId() != 0) {
            // Vérifier si le créneau existe
            Creneaux creneaux = creneauxRepo.findById(r.getCreneaux().getId())
                    .orElseThrow(() -> new RuntimeException("Creneau not found with id: " + r.getCreneaux().getId()));

            // Associer le créneau existant à la réservation
            r.setCreneaux(creneaux);
        }

        return reservationRepo.save(r);
    }
	@Override
	public Reservation updateReservation(Reservation r) {
	    Reservation existingReservation = reservationRepo.findById(r.getId())
	        .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + r.getId()));

	    // Mettre à jour uniquement les champs nécessaires
	    existingReservation.setCreneaux(r.getCreneaux());
	    existingReservation.setMatiere(r.getMatiere());
	    existingReservation.setNbrPage(r.getNbrPage());
	    existingReservation.setNiveau(r.getNiveau());
	    existingReservation.setSpecialite(r.getSpecialite());
	    existingReservation.setTypeImpression(r.getTypeImpression());

	    return reservationRepo.save(existingReservation);
	}


	@Override
	public void deleteReservation(Reservation r) {
        reservationRepo.delete(r);
    }

	@Override
	public void deleteReservationById(int id) {
        reservationRepo.deleteById(id);
    }


	@Override
	public Reservation getReservation(int id) {
		return reservationRepo.findById(id).get();
	}

	@Override
	public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();

	}

}
