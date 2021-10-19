package com.flight.booking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.booking.model.OperatingCity;
import com.flight.booking.repository.OperatingCityRepository;
import com.flight.booking.repository.entity.OperatingCityEntity;

@Service
public class OperatingCityService {

	@Autowired
	private OperatingCityRepository operatingCityRepository;

	public List<OperatingCity> addOperatingCitis(List<OperatingCity> operatingCities) {
		return operatingCities.parallelStream()
				.filter(city -> Optional
						.ofNullable(operatingCityRepository.findByCityCode(city.getCityCode()).orElse(null)).isEmpty())
				.map(city -> {
					OperatingCityEntity cityEntity = new OperatingCityEntity();
					cityEntity.setCityCode(city.getCityCode());
					cityEntity.setCityName(city.getCityName());
					cityEntity.setActive(city.isActive());
					cityEntity = operatingCityRepository.save(cityEntity);
					city.setCityId(cityEntity.getCityId());
					return city;
				}).collect(Collectors.toList());
	}

	public List<OperatingCity> getAllOperatingCities(boolean includeInactive) {
		return operatingCityRepository.findAll().parallelStream()
				.filter(cityEntity -> includeInactive ? true : cityEntity.isActive()).map(cityEntity -> {
					OperatingCity city = new OperatingCity();
					city.setCityId(cityEntity.getCityId());
					city.setCityCode(cityEntity.getCityCode());
					city.setCityName(cityEntity.getCityName());
					city.setActive(cityEntity.isActive());
					return city;
				}).collect(Collectors.toList());
	}

	public List<OperatingCity> updateOperatingCities(List<OperatingCity> operatingCities) {
		return operatingCities.parallelStream().map(city -> {
			OperatingCityEntity cityEntity = operatingCityRepository.findByCityCode(city.getCityCode())
					.orElse(new OperatingCityEntity());
			cityEntity.setCityName(city.getCityName());
			cityEntity.setActive(city.isActive());
			cityEntity = operatingCityRepository.save(cityEntity);
			city.setCityId(cityEntity.getCityId());
			return city;
		}).collect(Collectors.toList());
	}

}
