package com.plateformeDev.repos;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.plateformeDev.entities.Creneaux;
import com.plateformeDev.entities.User;

public interface CreneauxRepository extends JpaRepository<Creneaux, Integer> {
	@Query("SELECT COUNT(c) > 0 FROM Creneaux c WHERE c.date = :date AND "
	         + "(:heureDebut BETWEEN c.heureDebut AND c.heureFin OR :heureFin BETWEEN c.heureDebut AND c.heureFin "
	         + "OR c.heureDebut BETWEEN :heureDebut AND :heureFin)")
	    boolean existsByDateAndTimeOverlap(@Param("date") LocalDate date, @Param("heureDebut") LocalTime heureDebut, 
	                                       @Param("heureFin") LocalTime heureFin);
	
	
	@Query("SELECT c FROM Creneaux c WHERE c.statut = :statut AND (c.date < :nowDate OR (c.date = :nowDate AND c.heureFin <= :nowTime))")
	List<Creneaux> findAllByStatutAndDateTimeBefore(@Param("statut") String statut, @Param("nowDate") LocalDate nowDate, @Param("nowTime") LocalTime nowTime); 
	
	
	List<Creneaux> findBySecretaire(User secretaire);
}

