package com.flight.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.booking.repository.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

	Optional<RoleEntity> findByRoleName(String roleName);

}
