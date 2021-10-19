package com.flight.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.booking.repository.entity.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
}
