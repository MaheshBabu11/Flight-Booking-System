package com.flight.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.booking.repository.entity.OperatingCityEntity;

public interface OperatingCityRepository extends JpaRepository<OperatingCityEntity, Integer> {

	Optional<OperatingCityEntity> findByCityCode(String cityCode);

}
