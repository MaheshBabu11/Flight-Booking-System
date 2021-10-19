package com.flight.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.booking.repository.entity.FlightEntity;

public interface FlightRepository extends JpaRepository<FlightEntity, Integer> {

	Optional<FlightEntity> findByFlightNumber(String flightNumber);

}
