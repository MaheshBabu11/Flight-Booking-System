package com.flight.booking.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.booking.model.Airline;
import com.flight.booking.model.Flight;
import com.flight.booking.model.Meal;
import com.flight.booking.model.OperatingCity;
import com.flight.booking.model.Roaster;
import com.flight.booking.model.RoasterStatus;
import com.flight.booking.repository.CustomJpaRepository;
import com.flight.booking.repository.FlightRepository;
import com.flight.booking.repository.OperatingCityRepository;
import com.flight.booking.repository.RoasterRepository;
import com.flight.booking.repository.RoasterStatusRepository;
import com.flight.booking.repository.entity.FlightEntity;
import com.flight.booking.repository.entity.RoasterEntity;
import com.flight.booking.util.FlightUtils;

@Service
public class RoasterService {

	@Autowired
	private RoasterRepository roasterRepository;

	@Autowired
	private CustomJpaRepository customJpaRepository;

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private OperatingCityRepository cityRepository;

	@Autowired
	private RoasterStatusRepository roasterStatusRepository;

	public List<Roaster> scheduleRoaster(List<Roaster> roasters) {
		return roasters.parallelStream().map(roaster -> {
			RoasterEntity roasterEntity = new RoasterEntity();
			if (roaster.getRoasterId() != null) {
				roasterEntity = roasterRepository.findById(roaster.getRoasterId()).get();
			}
			roasterEntity.setFlight(flightRepository.findById(roaster.getFlight().getFlightId()).get());
			roasterEntity.setFrom(cityRepository.findById(roaster.getFrom().getCityId()).get());
			roasterEntity.setTo(cityRepository.findById(roaster.getTo().getCityId()).get());
			try {
				roasterEntity.setRoasterDate(new SimpleDateFormat("dd/MM/yyyy").parse(roaster.getRoasterDate()));
			} catch (ParseException e) {
				e.printStackTrace();
				throw new RuntimeException("unparsable date " + roaster.getRoasterDate());
			}
			roasterEntity.setDepurture(roaster.getDepurture());
			roasterEntity.setArrival(roaster.getArrival());
			roasterEntity.setDelayTimeInMins(roaster.getDelayTimeInMins());
			roasterEntity.setBusinessClassSeatsAvailable(roaster.getBusinessClassSeatsAvailable());
			roasterEntity.setNonBusinessClassSeatsAvailable(roaster.getNonBusinessClassSeatsAvailable());
			roasterEntity.setBusinessClassSeatsPrice(roaster.getBusinessClassSeatsPrice());
			roasterEntity.setNonBusinessClassSeatsPrice(roaster.getNonBusinessClassSeatsPrice());
			roasterEntity.setRemarks(roaster.getRemarks());
			roasterEntity.setStatus(roasterStatusRepository.findById(roaster.getStatus().getStatusId()).get());
			roasterEntity = roasterRepository.save(roasterEntity);
			roaster.setRoasterId(roasterEntity.getRoasterId());
			FlightEntity flightEntity = roasterEntity.getFlight();
			Flight flight = new Flight();
			flight.setFlightId(flightEntity.getFlightId());
			flight.setFlightNumber(flightEntity.getFlightNumber());
			Airline airline = new Airline();
			airline.setAirlineId(flightEntity.getAirline().getAirlineId());
			airline.setAirlineName(flightEntity.getAirline().getAirlineName());
			flight.setAirline(airline);
			OperatingCity from = new OperatingCity();
			from.setCityId(flightEntity.getFrom().getCityId());
			from.setCityCode(flightEntity.getFrom().getCityCode());
			from.setCityName(flightEntity.getFrom().getCityName());
			flight.setFrom(from);
			OperatingCity to = new OperatingCity();
			to.setCityId(flightEntity.getTo().getCityId());
			to.setCityCode(flightEntity.getTo().getCityCode());
			to.setCityName(flightEntity.getTo().getCityName());
			flight.setTo(to);
			flight.setStartTime(flightEntity.getStartTime());
			flight.setEndTime(flightEntity.getEndTime());
			flight.setDuration(flightEntity.getDuration());
			flight.setBusinessClassSeats(flightEntity.getBusinessClassSeats());
			flight.setNonBusinessClassSeats(flightEntity.getNonBusinessClassSeats());
			flight.setBusinessClassSeatsPrice(flightEntity.getBusinessClassSeatsPrice());
			flight.setNonBusinessClassSeatsPrice(flightEntity.getNonBusinessClassSeatsPrice());
			flight.setRowCount(flightEntity.getRowCount());
			flight.setColumnCount(flightEntity.getColumnCount());
			flight.setActive(flightEntity.isActive());
			Meal meal = new Meal();
			meal.setMealId(flightEntity.getMeal().getMealId());
			meal.setMealType(flightEntity.getMeal().getMealType());
			meal.setMealDescription(flightEntity.getMeal().getMealDescription());
			flight.setMeal(meal);
			roaster.setFlight(flight);
			OperatingCity roasterFrom = new OperatingCity();
			roasterFrom.setCityId(roasterEntity.getFrom().getCityId());
			roasterFrom.setCityCode(roasterEntity.getFrom().getCityCode());
			roasterFrom.setCityName(roasterEntity.getFrom().getCityName());
			roaster.setFrom(roasterFrom);
			OperatingCity roasterTo = new OperatingCity();
			roasterTo.setCityId(roasterEntity.getTo().getCityId());
			roasterTo.setCityCode(roasterEntity.getTo().getCityCode());
			roasterTo.setCityName(roasterEntity.getTo().getCityName());
			roaster.setTo(roasterTo);
			RoasterStatus roasterStatus = new RoasterStatus();
			roasterStatus.setStatusId(roasterEntity.getStatus().getStatusId());
			roasterStatus.setStatusName(roasterEntity.getStatus().getStatusName());
			roaster.setStatus(roasterStatus);
			return roaster;
		}).collect(Collectors.toList());
	}

	public List<Roaster> getRoastersForDate(String date) {
		List<Roaster> roasters = new ArrayList<>();
		List<FlightEntity> availableFlights = customJpaRepository.findFlightByDate(date);
		List<RoasterEntity> scheduledRoasters = null;
		try {
			scheduledRoasters = roasterRepository.findAllByRoasterDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("unparsable date " + date);
		}
		for (FlightEntity flightEntity : availableFlights) {
			Roaster roaster = null;
			RoasterEntity roasterEntity = scheduledRoasters.parallelStream()
					.filter(rEntity -> rEntity.getFlight().getFlightId() == flightEntity.getFlightId()).findFirst()
					.orElse(null);
			if (roasterEntity != null) {
				roaster = new Roaster();
				roaster.setRoasterId(roasterEntity.getRoasterId());
				FlightEntity roasterFlightEntity = roasterEntity.getFlight();
				Flight flight = new Flight();
				flight.setFlightId(roasterFlightEntity.getFlightId());
				flight.setFlightNumber(roasterFlightEntity.getFlightNumber());
				Airline airline = new Airline();
				airline.setAirlineId(roasterFlightEntity.getAirline().getAirlineId());
				airline.setAirlineName(roasterFlightEntity.getAirline().getAirlineName());
				flight.setAirline(airline);
				OperatingCity from = new OperatingCity();
				from.setCityId(roasterFlightEntity.getFrom().getCityId());
				from.setCityCode(roasterFlightEntity.getFrom().getCityCode());
				from.setCityName(roasterFlightEntity.getFrom().getCityName());
				flight.setFrom(from);
				OperatingCity to = new OperatingCity();
				to.setCityId(roasterFlightEntity.getTo().getCityId());
				to.setCityCode(roasterFlightEntity.getTo().getCityCode());
				to.setCityName(roasterFlightEntity.getTo().getCityName());
				flight.setTo(to);
				flight.setStartTime(roasterFlightEntity.getStartTime());
				flight.setEndTime(roasterFlightEntity.getEndTime());
				flight.setDuration(roasterFlightEntity.getDuration());
				flight.setBusinessClassSeats(roasterFlightEntity.getBusinessClassSeats());
				flight.setNonBusinessClassSeats(roasterFlightEntity.getNonBusinessClassSeats());
				flight.setBusinessClassSeatsPrice(roasterFlightEntity.getBusinessClassSeatsPrice());
				flight.setNonBusinessClassSeatsPrice(roasterFlightEntity.getNonBusinessClassSeatsPrice());
				flight.setRowCount(roasterFlightEntity.getRowCount());
				flight.setColumnCount(roasterFlightEntity.getColumnCount());
				flight.setActive(roasterFlightEntity.isActive());
				Meal meal = new Meal();
				meal.setMealId(roasterFlightEntity.getMeal().getMealId());
				meal.setMealType(roasterFlightEntity.getMeal().getMealType());
				meal.setMealDescription(roasterFlightEntity.getMeal().getMealDescription());
				flight.setMeal(meal);
				roaster.setFlight(flight);
				OperatingCity roasterFrom = new OperatingCity();
				roasterFrom.setCityId(roasterEntity.getFrom().getCityId());
				roasterFrom.setCityCode(roasterEntity.getFrom().getCityCode());
				roasterFrom.setCityName(roasterEntity.getFrom().getCityName());
				roaster.setFrom(roasterFrom);
				OperatingCity roasterTo = new OperatingCity();
				roasterTo.setCityId(roasterEntity.getTo().getCityId());
				roasterTo.setCityCode(roasterEntity.getTo().getCityCode());
				roasterTo.setCityName(roasterEntity.getTo().getCityName());
				roaster.setTo(roasterTo);
				RoasterStatus roasterStatus = new RoasterStatus();
				roasterStatus.setStatusId(roasterEntity.getStatus().getStatusId());
				roasterStatus.setStatusName(roasterEntity.getStatus().getStatusName());
				roaster.setStatus(roasterStatus);
				roaster.setRoasterDate(FlightUtils.getFormattedDate(roasterEntity.getRoasterDate(), "dd/MM/yyyy"));
				roaster.setDepurture(roasterEntity.getDepurture());
				roaster.setArrival(roasterEntity.getArrival());
				roaster.setDelayTimeInMins(roasterEntity.getDelayTimeInMins());
				roaster.setBusinessClassSeatsAvailable(roasterEntity.getBusinessClassSeatsAvailable());
				roaster.setNonBusinessClassSeatsAvailable(roasterEntity.getNonBusinessClassSeatsAvailable());
				roaster.setBusinessClassSeatsPrice(roasterEntity.getBusinessClassSeatsPrice());
				roaster.setNonBusinessClassSeatsPrice(roasterEntity.getNonBusinessClassSeatsPrice());
				roaster.setRemarks(roasterEntity.getRemarks());
			} else {
				roaster = new Roaster();
				Flight flight = new Flight();
				flight.setFlightId(flightEntity.getFlightId());
				flight.setFlightNumber(flightEntity.getFlightNumber());
				Airline airline = new Airline();
				airline.setAirlineId(flightEntity.getAirline().getAirlineId());
				airline.setAirlineName(flightEntity.getAirline().getAirlineName());
				flight.setAirline(airline);
				OperatingCity from = new OperatingCity();
				from.setCityId(flightEntity.getFrom().getCityId());
				from.setCityCode(flightEntity.getFrom().getCityCode());
				from.setCityName(flightEntity.getFrom().getCityName());
				flight.setFrom(from);
				OperatingCity to = new OperatingCity();
				to.setCityId(flightEntity.getTo().getCityId());
				to.setCityCode(flightEntity.getTo().getCityCode());
				to.setCityName(flightEntity.getTo().getCityName());
				flight.setTo(to);
				flight.setStartTime(flightEntity.getStartTime());
				flight.setEndTime(flightEntity.getEndTime());
				flight.setDuration(flightEntity.getDuration());
				flight.setBusinessClassSeats(flightEntity.getBusinessClassSeats());
				flight.setNonBusinessClassSeats(flightEntity.getNonBusinessClassSeats());
				flight.setBusinessClassSeatsPrice(flightEntity.getBusinessClassSeatsPrice());
				flight.setNonBusinessClassSeatsPrice(flightEntity.getNonBusinessClassSeatsPrice());
				flight.setRowCount(flightEntity.getRowCount());
				flight.setColumnCount(flightEntity.getColumnCount());
				flight.setActive(flightEntity.isActive());
				Meal meal = new Meal();
				meal.setMealId(flightEntity.getMeal().getMealId());
				meal.setMealType(flightEntity.getMeal().getMealType());
				meal.setMealDescription(flightEntity.getMeal().getMealDescription());
				flight.setMeal(meal);
				roaster.setFlight(flight);
				OperatingCity roasterFrom = new OperatingCity();
				roasterFrom.setCityId(flightEntity.getFrom().getCityId());
				roasterFrom.setCityCode(flightEntity.getFrom().getCityCode());
				roasterFrom.setCityName(flightEntity.getFrom().getCityName());
				roaster.setFrom(roasterFrom);
				OperatingCity roasterTo = new OperatingCity();
				roasterTo.setCityId(flightEntity.getTo().getCityId());
				roasterTo.setCityCode(flightEntity.getTo().getCityCode());
				roasterTo.setCityName(flightEntity.getTo().getCityName());
				roaster.setTo(roasterTo);
				RoasterStatus roasterStatus = new RoasterStatus();
				roasterStatus.setStatusName("NOT-SCHEDULED");
				roaster.setStatus(roasterStatus);
				roaster.setRoasterDate(date);
				roaster.setDepurture(flightEntity.getStartTime());
				roaster.setArrival(flightEntity.getEndTime());
				roaster.setDelayTimeInMins(0);
				roaster.setBusinessClassSeatsAvailable(flightEntity.getBusinessClassSeats());
				roaster.setNonBusinessClassSeatsAvailable(flightEntity.getNonBusinessClassSeats());
				roaster.setBusinessClassSeatsPrice(flightEntity.getBusinessClassSeatsPrice());
				roaster.setNonBusinessClassSeatsPrice(flightEntity.getNonBusinessClassSeatsPrice());
				roaster.setRemarks("Not scheduled");
			}
			roasters.add(roaster);
		}
		return roasters;
	}

}
