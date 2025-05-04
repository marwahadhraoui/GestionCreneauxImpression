package com.plateformeDev.service;


import com.plateformeDev.entities.Examen;
import com.plateformeDev.entities.Reservation;
import com.plateformeDev.entities.Enseignant;
import com.plateformeDev.repos.ExamenRepository;
import com.plateformeDev.repos.ReservationRepository;
import com.plateformeDev.repos.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository enseignantRepository;

    /**
     * Récupère les examens dans 2-3 jours pour lesquels aucun enseignant n'a encore réservé de créneau
     */
    
    public List<Examen> getAlertesExamensSansReservation() {
        LocalDate aujourdHui = LocalDate.now();
        LocalDate dansDeuxJours = aujourdHui.plusDays(2);
        LocalDate dansTroisJours = aujourdHui.plusDays(3);

        List<Examen> examens = examenRepository.findExamenBetweenDates(dansDeuxJours, dansTroisJours);

        return examens.stream()
                .filter(examen -> {
                    Enseignant enseignant = examen.getEnseignant();
                    return reservationRepository.findByEnseignantId((long) enseignant.getId()).isEmpty();
                })
                .collect(Collectors.toList());
    } 
    
    public List<Examen> getExamens()
    {
    	return examenRepository.findAll();
    }
}
