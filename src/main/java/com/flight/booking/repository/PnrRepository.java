package com.flight.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.booking.repository.entity.PnrEntity;

public interface PnrRepository extends JpaRepository<PnrEntity, Integer> {

	Optional<PnrEntity> findByPnrNumber(String pnrNumber);

}
