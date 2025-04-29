package com.plateformeDev.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.plateformeDev.entities.Creneaux;
import com.plateformeDev.entities.User;
import com.plateformeDev.repos.CreneauxRepository;
import com.plateformeDev.repos.UserRepository;

public class CreneauxServiceImplTest {

    @Mock
    private CreneauxRepository creneauxRepo;

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private CreneauxServiceImpl creneauxService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCreneau_withValidSecretaireAndNoConflict() {
        // Arrange
        User secretaire = new User();
        secretaire.setId(1);
        secretaire.setRole("SECRETAIRE");

        Creneaux creneau = new Creneaux();
        creneau.setSecretaire(secretaire);
        creneau.setDate(LocalDate.now());
        creneau.setHeureDebut(LocalTime.of(10, 0));
        creneau.setHeureFin(LocalTime.of(11, 0));
        
        /*The when(...) function is used to tell Mockito:
		When this method is called(for exemple userRepo.findById(1)) during the test, return this value(Optional.of(secretaire))
		instead of executing the real method
		*/

        when(userRepo.findById(1)).thenReturn(Optional.of(secretaire));
        //any() : Accept any value of the given type as a paramete
        when(creneauxRepo.existsByDateAndTimeOverlap(any(), any(), any())).thenReturn(false);
        when(creneauxRepo.save(any())).thenReturn(creneau);

        // Act
        Creneaux saved = creneauxService.saveCreneau(creneau);

        // Assert
        assertNotNull(saved);
        // verify(..) : Make sure that the method save(creneau) was called exactly once
        verify(creneauxRepo, times(1)).save(creneau);
    }

    @Test
    void testSaveCreneau_withNonSecretaireUser_shouldThrowException() {
        // Arrange
        User nonSecretaire = new User();
        nonSecretaire.setId(2);
        nonSecretaire.setRole("Enseignant");

        Creneaux creneau = new Creneaux();
        creneau.setSecretaire(nonSecretaire);

        when(userRepo.findById(2)).thenReturn(Optional.of(nonSecretaire));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            creneauxService.saveCreneau(creneau);
        });

        assertTrue(exception.getMessage().contains("n'est pas un secrétaire"));
        verify(creneauxRepo, never()).save(any());
    }

    @Test
    void testSaveCreneau_whenCreneauAlreadyReserved_shouldThrowException() {
        // Arrange
        User secretaire = new User();
        secretaire.setId(1);
        secretaire.setRole("SECRETAIRE");

        Creneaux creneau = new Creneaux();
        creneau.setSecretaire(secretaire);
        creneau.setDate(LocalDate.now());
        creneau.setHeureDebut(LocalTime.of(10, 0));
        creneau.setHeureFin(LocalTime.of(11, 0));

        when(userRepo.findById(1)).thenReturn(Optional.of(secretaire));
        when(creneauxRepo.existsByDateAndTimeOverlap(any(), any(), any())).thenReturn(true);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            creneauxService.saveCreneau(creneau);
        });

        assertEquals("This time slot is already reserved.", exception.getMessage());
    }
}
