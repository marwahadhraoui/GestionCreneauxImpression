package com.plateformeDev.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plateformeDev.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
