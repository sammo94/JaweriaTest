package com.example.flight_reservation.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flight_reservation.dto.ReservationUpdateRequest;
import com.example.flight_reservation.entities.Reservation;
import com.example.flight_reservation.repositories.ReservationRepository;


@RestController
public class ReservationRestController {
	
	@Autowired
	ReservationRepository reservationRepo;
	private Optional<Reservation> findById;
	
	@RequestMapping("/reservation/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		findById = reservationRepo.findById(id);
		Reservation reservation = findById.get();
		return reservation;
	}
	
	
	@RequestMapping("/reservation")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		Optional<Reservation> findById = reservationRepo.findById(request.getId());
		Reservation reservation = findById.get();
		reservation.setCheckedIn(request.isCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		return reservationRepo.save(reservation);
	}
	
	
}
