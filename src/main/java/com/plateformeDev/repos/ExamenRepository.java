package com.plateformeDev.repos;

 

import com.plateformeDev.entities.Examen;
import com.plateformeDev.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen, Long> {

    @Query("SELECT e FROM Examen e WHERE e.dateExamen BETWEEN :startDate AND :endDate AND e.enseignant IS NOT NULL")
    List<Examen> findExamenBetweenDates(LocalDate startDate, LocalDate endDate);

    List<Examen> findByEnseignant(Enseignant enseignant);
}
