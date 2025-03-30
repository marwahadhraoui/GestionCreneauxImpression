package com.plateformeDev.service;

import java.util.List;

import com.plateformeDev.entities.Reservation;

public interface ReservationService {

	Reservation saveReservation(Reservation r);
    Reservation updateReservation(Reservation r);
    void deleteReservation(Reservation r);
    void deleteReservationById(int id);
    Reservation getReservation(int id);
    List<Reservation> getAllReservations();
}
