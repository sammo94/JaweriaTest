package com.example.flight_reservation.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.flight_reservation.dto.ReservationRequest;
import com.example.flight_reservation.entities.Flight;
import com.example.flight_reservation.entities.Passenger;
import com.example.flight_reservation.entities.Reservation;
import com.example.flight_reservation.repositories.FlightRepository;
import com.example.flight_reservation.repositories.PassengerRepository;
import com.example.flight_reservation.repositories.ReservationRepository;



@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	FlightRepository flightRepo;
	
	@Autowired
	PassengerRepository PassengerRepo;
	
	@Autowired
	ReservationRepository reservationRepo;

	
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		long flightId = request.getFlightId();
		Optional<Flight> findById = flightRepo.findById(flightId);
		Flight flight = findById.get();
		
		
		Passenger p = new Passenger();
		p.setFirstName(request.getPassengerFirstName());
		p.setLastName(request.getPassengerLastName());
		p.setEmail(request.getPassengerEmail());
		p.setPhone(request.getPassengerMobile());
		Passenger savedPassenger = PassengerRepo.save(p);
		
		Reservation r = new Reservation();
		r.setFlight(flight);
		r.setPassenger(savedPassenger);
		r.setCheckedIn(false);
		Reservation savedReservation = reservationRepo.save(r);
		
		return savedReservation ;
	}

}
