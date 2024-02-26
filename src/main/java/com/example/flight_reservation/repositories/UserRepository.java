package com.example.flight_reservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flight_reservation.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
