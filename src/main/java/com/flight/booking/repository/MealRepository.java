package com.flight.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.booking.repository.entity.MealEntity;

public interface MealRepository extends JpaRepository<MealEntity, Integer> {
}
