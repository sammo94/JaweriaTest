package com.example.flight_reservation.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.flight_reservation.dto.ReservationRequest;
import com.example.flight_reservation.entities.Flight;
import com.example.flight_reservation.entities.Reservation;
import com.example.flight_reservation.repositories.FlightRepository;
import com.example.flight_reservation.services.ReservationService;

@Controller
public class ReservationController {
	
	@Autowired
	FlightRepository flightRepo;

	@Autowired
	ReservationService reservationService;

	@RequestMapping("showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		Optional<Flight> findById = flightRepo.findById(flightId);
		Flight flight = findById.get();
		modelMap.addAttribute("flight", flight);
		return "completeReservation";
	}

	@RequestMapping("/confirmRegistration")
	public String confirmRegistration(ReservationRequest request, ModelMap modelMap) {
		Reservation savedReservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg", "You reservationis confirmed.You Reservation id is " + savedReservation.getId());
		return "finalConfirmation";
	}

}
