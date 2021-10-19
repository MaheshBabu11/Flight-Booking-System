package com.flight.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.booking.repository.entity.RoasterStatusEntity;

public interface RoasterStatusRepository extends JpaRepository<RoasterStatusEntity, Integer> {
}
