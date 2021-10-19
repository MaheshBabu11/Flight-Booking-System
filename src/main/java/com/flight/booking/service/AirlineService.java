package com.flight.booking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.booking.model.Airline;
import com.flight.booking.repository.AirlineRepository;
import com.flight.booking.repository.FileRepository;
import com.flight.booking.repository.entity.AirlineEntity;

@Service
public class AirlineService {

	@Autowired
	private AirlineRepository airlineRepository;

	@Autowired
	private FileRepository fileRepository;

	public List<Airline> addAirlines(List<Airline> airlines) {
		return airlines.parallelStream()
				.filter(airline -> Optional
						.ofNullable(airlineRepository.findByAirlineName(airline.getAirlineName()).orElse(null))
						.isEmpty())
				.map(airline -> {
					AirlineEntity airlineEntity = new AirlineEntity();
					airlineEntity.setAirlineName(airline.getAirlineName());
					airlineEntity.setAirlineContactNumber(airline.getAirlineContactNumber());
					airlineEntity.setAirlineAddress(airline.getAirlineAddress());
					airlineEntity.setActive(airline.isActive());
					airlineEntity.setAirlineLogo(fileRepository.getById(airline.getAirlineLogoId()));
					airlineEntity = airlineRepository.save(airlineEntity);
					airline.setAirlineId(airlineEntity.getAirlineId());
					airline.setAirlineLogo(airlineEntity.getAirlineLogo().getFileContent());
					airline.setAirlineLogoType(airlineEntity.getAirlineLogo().getFileType());
					return airline;
				}).collect(Collectors.toList());
	}

	public List<Airline> getAllAirlines(boolean includeInactive) {
		return airlineRepository.findAll().parallelStream()
				.filter(airlineEntity -> includeInactive ? true : airlineEntity.isActive()).map(airlineEntity -> {
					Airline airline = new Airline();
					airline.setAirlineId(airlineEntity.getAirlineId());
					airline.setAirlineName(airlineEntity.getAirlineName());
					airline.setAirlineContactNumber(airlineEntity.getAirlineContactNumber());
					airline.setAirlineAddress(airlineEntity.getAirlineAddress());
					airline.setActive(airlineEntity.isActive());
					airline.setAirlineLogo(airlineEntity.getAirlineLogo().getFileContent());
					airline.setAirlineLogoType(airlineEntity.getAirlineLogo().getFileType());
					return airline;
				}).collect(Collectors.toList());
	}

	public List<Airline> updateAirlines(List<Airline> airlines) {
		return airlines.parallelStream().map(airline -> {
			AirlineEntity airlineEntity = airlineRepository.findById(airline.getAirlineId()).get();
			airlineEntity.setAirlineName(airline.getAirlineName());
			airlineEntity.setAirlineContactNumber(airline.getAirlineContactNumber());
			airlineEntity.setAirlineAddress(airline.getAirlineAddress());
			airlineEntity.setActive(airline.isActive());
			airlineEntity.setAirlineLogo(fileRepository.getById(airline.getAirlineLogoId()));
			airlineEntity = airlineRepository.save(airlineEntity);
			airline.setAirlineId(airlineEntity.getAirlineId());
			airline.setAirlineLogo(airlineEntity.getAirlineLogo().getFileContent());
			airline.setAirlineLogoType(airlineEntity.getAirlineLogo().getFileType());
			return airline;
		}).collect(Collectors.toList());
	}

	public List<Integer> deleteAirlines(List<Integer> airlineIds) {
		return airlineIds.parallelStream().map(airlineId -> {
			AirlineEntity airlineEntity = airlineRepository.findById(airlineId).get();
			airlineEntity.setActive(false);
			airlineEntity = airlineRepository.save(airlineEntity);
			return airlineId;
		}).collect(Collectors.toList());
	}

	public int changeAirlineStatus(int airlineId, boolean status) {
		AirlineEntity airlineEntity = airlineRepository.findById(airlineId).get();
		airlineEntity.setActive(status);
		airlineEntity = airlineRepository.save(airlineEntity);
		return airlineId;
	}

}
