package com.plateformeDev.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plateformeDev.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> { 
	
	List<Reservation> findByEnseignantId(Long enseignantId);

}
