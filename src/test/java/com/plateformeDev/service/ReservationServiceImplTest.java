package com.plateformeDev.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import com.plateformeDev.entities.Reservation;
import com.plateformeDev.entities.Creneaux;
import com.plateformeDev.repos.ReservationRepository;
import com.plateformeDev.repos.CreneauxRepository;
import com.plateformeDev.service.ReservationImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepo;

    @Mock
    private CreneauxRepository creneauxRepo;

    @InjectMocks
    private ReservationImpl reservationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveReservation_WithExistingCreneaux() {
        Creneaux creneaux = new Creneaux();
        creneaux.setId(1);

        Reservation reservation = new Reservation();
        reservation.setCreneaux(creneaux);

        when(creneauxRepo.findById(1)).thenReturn(Optional.of(creneaux));
        when(reservationRepo.save(any(Reservation.class))).thenReturn(reservation);

        Reservation saved = reservationService.saveReservation(reservation);
        assertNotNull(saved);
        verify(creneauxRepo).findById(1);
        verify(reservationRepo).save(reservation);
    }

    @Test
    public void testUpdateReservation() {
        Reservation oldReservation = new Reservation();
        oldReservation.setId(1);

        Reservation newReservation = new Reservation();
        newReservation.setId(1);
        newReservation.setMatiere("Math");

        when(reservationRepo.findById(1)).thenReturn(Optional.of(oldReservation));
        when(reservationRepo.save(any(Reservation.class))).thenReturn(newReservation);

        Reservation updated = reservationService.updateReservation(newReservation);
        assertEquals("Math", updated.getMatiere());
        verify(reservationRepo).findById(1);
        verify(reservationRepo).save(any(Reservation.class));
    }

    @Test
    public void testDeleteReservationById() {
        reservationService.deleteReservationById(5);
        verify(reservationRepo).deleteById(5);
    }

    @Test
    public void testGetReservation() {
        Reservation reservation = new Reservation();
        reservation.setId(10);
        when(reservationRepo.findById(10)).thenReturn(Optional.of(reservation));

        Reservation result = reservationService.getReservation(10);
        assertEquals(10, result.getId());
    }

    @Test
    public void testGetAllReservations() {
        List<Reservation> list = Arrays.asList(new Reservation(), new Reservation());
        when(reservationRepo.findAll()).thenReturn(list);

        List<Reservation> result = reservationService.getAllReservations();
        assertEquals(2, result.size());
    }
}
