package com.flight.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.booking.repository.entity.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

	Optional<TicketEntity> findByTicketNumber(String ticketNumber);

}
