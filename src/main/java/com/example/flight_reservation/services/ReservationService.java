package com.example.flight_reservation.services;

import com.example.flight_reservation.dto.ReservationRequest;
import com.example.flight_reservation.entities.Reservation;

public interface ReservationService {

	Reservation bookFlight(ReservationRequest request);

}
