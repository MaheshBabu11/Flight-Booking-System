package com.flight.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.booking.repository.entity.AirlineEntity;

public interface AirlineRepository extends JpaRepository<AirlineEntity, Integer> {

	Optional<AirlineEntity> findByAirlineName(String airlineName);

}
