package com.flight.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.booking.repository.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findByEmail(String email);
}
