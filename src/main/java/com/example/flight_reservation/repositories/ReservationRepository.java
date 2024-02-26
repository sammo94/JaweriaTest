package com.example.flight_reservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flight_reservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
