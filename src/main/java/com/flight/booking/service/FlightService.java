package com.flight.booking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.booking.model.Airline;
import com.flight.booking.model.Flight;
import com.flight.booking.model.Meal;
import com.flight.booking.model.OperatingCity;
import com.flight.booking.repository.AirlineRepository;
import com.flight.booking.repository.CustomJpaRepository;
import com.flight.booking.repository.FlightRepository;
import com.flight.booking.repository.MealRepository;
import com.flight.booking.repository.OperatingCityRepository;
import com.flight.booking.repository.entity.FlightEntity;

@Service
public class FlightService {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private CustomJpaRepository customJpaRepository;

	@Autowired
	private AirlineRepository airlineRepository;

	@Autowired
	private OperatingCityRepository cityRepository;

	@Autowired
	private MealRepository mealRepository;

	public List<Flight> addFlights(List<Flight> flights) {
		return flights.parallelStream()
				.filter(flight -> Optional
						.ofNullable(flightRepository.findByFlightNumber(flight.getFlightNumber()).orElse(null))
						.isEmpty())
				.map(flight -> {
					FlightEntity flightEntity = new FlightEntity();
					flightEntity.setFlightNumber(flight.getFlightNumber());
					flightEntity.setAirline(Optional
							.ofNullable(airlineRepository.findById(flight.getAirline().getAirlineId()).orElse(null))
							.get());
					flightEntity.setFrom(Optional
							.ofNullable(cityRepository.findById(flight.getFrom().getCityId()).orElse(null)).get());
					flightEntity.setTo(Optional
							.ofNullable(cityRepository.findById(flight.getTo().getCityId()).orElse(null)).get());
					flightEntity.setStartTime(flight.getStartTime());
					flightEntity.setEndTime(flight.getEndTime());
					flightEntity.setDuration(flight.getDuration());
					flightEntity.setOnSunday(flight.isOnSunday());
					flightEntity.setOnMonday(flight.isOnMonday());
					flightEntity.setOnTuesday(flight.isOnTuesday());
					flightEntity.setOnWednesday(flight.isOnWednesday());
					flightEntity.setOnThursday(flight.isOnThursday());
					flightEntity.setOnFriday(flight.isOnFriday());
					flightEntity.setOnSaturday(flight.isOnSaturday());
					flightEntity.setInstrument(flight.getInstrument());
					flightEntity.setBusinessClassSeats(flight.getBusinessClassSeats());
					flightEntity.setNonBusinessClassSeats(flight.getNonBusinessClassSeats());
					flightEntity.setBusinessClassSeatsPrice(flight.getBusinessClassSeatsPrice());
					flightEntity.setNonBusinessClassSeatsPrice(flight.getNonBusinessClassSeatsPrice());
					flightEntity.setRowCount(flight.getRowCount());
					flightEntity.setColumnCount(flight.getColumnCount());
					flightEntity.setActive(flight.isActive());
					flightEntity.setMeal(Optional
							.ofNullable(mealRepository.findById(flight.getMeal().getMealId()).orElse(null)).get());
					flightEntity = flightRepository.save(flightEntity);
					flight.setFlightId(flightEntity.getFlightId());
					Airline airline = new Airline();
					airline.setAirlineId(flightEntity.getAirline().getAirlineId());
					airline.setAirlineName(flightEntity.getAirline().getAirlineName());
					airline.setAirlineLogo(flightEntity.getAirline().getAirlineLogo().getFileContent());
					airline.setAirlineLogoType(flightEntity.getAirline().getAirlineLogo().getFileType());
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
					Meal meal = new Meal();
					meal.setMealId(flightEntity.getMeal().getMealId());
					meal.setMealType(flightEntity.getMeal().getMealType());
					meal.setMealDescription(flightEntity.getMeal().getMealDescription());
					flight.setMeal(meal);
					return flight;
				}).collect(Collectors.toList());
	}

	public List<Flight> getAllFlights(boolean includeInactive) {
		return flightRepository.findAll().parallelStream()
				.filter(flightEntity -> includeInactive ? true : flightEntity.isActive()).map(flightEntity -> {
					Flight flight = new Flight();
					flight.setFlightId(flightEntity.getFlightId());
					flight.setFlightNumber(flightEntity.getFlightNumber());
					Airline airline = new Airline();
					airline.setAirlineId(flightEntity.getAirline().getAirlineId());
					airline.setAirlineName(flightEntity.getAirline().getAirlineName());
					airline.setAirlineLogo(flightEntity.getAirline().getAirlineLogo().getFileContent());
					airline.setAirlineLogoType(flightEntity.getAirline().getAirlineLogo().getFileType());
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
					flight.setOnSunday(flightEntity.isOnSunday());
					flight.setOnMonday(flightEntity.isOnMonday());
					flight.setOnTuesday(flightEntity.isOnTuesday());
					flight.setOnWednesday(flightEntity.isOnWednesday());
					flight.setOnThursday(flightEntity.isOnThursday());
					flight.setOnFriday(flightEntity.isOnFriday());
					flight.setOnSaturday(flightEntity.isOnSaturday());
					flight.setInstrument(flightEntity.getInstrument());
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
					return flight;
				}).collect(Collectors.toList());
	}

	public List<Flight> updateFlights(List<Flight> flights) {
		return flights.parallelStream().map(flight -> {
			FlightEntity flightEntity = flightRepository.findById(flight.getFlightId()).get();
			flightEntity.setFlightNumber(flight.getFlightNumber());
			flightEntity.setAirline(airlineRepository.getById(flight.getAirline().getAirlineId()));
			flightEntity.setFrom(cityRepository.getById(flight.getFrom().getCityId()));
			flightEntity.setTo(cityRepository.getById(flight.getTo().getCityId()));
			flightEntity.setStartTime(flight.getStartTime());
			flightEntity.setEndTime(flight.getEndTime());
			flightEntity.setDuration(flight.getDuration());
			flightEntity.setOnSunday(flight.isOnSunday());
			flightEntity.setOnMonday(flight.isOnMonday());
			flightEntity.setOnTuesday(flight.isOnTuesday());
			flightEntity.setOnWednesday(flight.isOnWednesday());
			flightEntity.setOnThursday(flight.isOnThursday());
			flightEntity.setOnFriday(flight.isOnFriday());
			flightEntity.setOnSaturday(flight.isOnSaturday());
			flightEntity.setInstrument(flight.getInstrument());
			flightEntity.setBusinessClassSeats(flight.getBusinessClassSeats());
			flightEntity.setNonBusinessClassSeats(flight.getNonBusinessClassSeats());
			flightEntity.setBusinessClassSeatsPrice(flight.getBusinessClassSeatsPrice());
			flightEntity.setNonBusinessClassSeatsPrice(flight.getNonBusinessClassSeatsPrice());
			flightEntity.setRowCount(flight.getRowCount());
			flightEntity.setColumnCount(flight.getColumnCount());
			flightEntity.setActive(flight.isActive());
			flightEntity.setMeal(mealRepository.getById(flight.getMeal().getMealId()));
			flightEntity = flightRepository.save(flightEntity);
			flight.setFlightId(flightEntity.getFlightId());
			return flight;
		}).collect(Collectors.toList());
	}

	public List<Integer> deleteFlights(List<Integer> flightIds) {
		return flightIds.parallelStream().map(flightId -> {
			FlightEntity flightEntity = flightRepository.findById(flightId).get();
			flightEntity.setActive(false);
			flightEntity = flightRepository.save(flightEntity);
			return flightId;
		}).collect(Collectors.toList());
	}

	public int changeFlightStatus(int flightId, boolean status) {
		FlightEntity flightEntity = flightRepository.findById(flightId).get();
		flightEntity.setActive(status);
		flightEntity = flightRepository.save(flightEntity);
		return flightId;
	}

	public List<Flight> getFlightsForDate(String date, boolean includeInactive) {
		return customJpaRepository.findFlightByDate(date).parallelStream()
				.filter(flightEntity -> includeInactive ? true : flightEntity.isActive()).map(flightEntity -> {
					Flight flight = new Flight();
					flight.setFlightId(flightEntity.getFlightId());
					flight.setFlightNumber(flightEntity.getFlightNumber());
					Airline airline = new Airline();
					airline.setAirlineId(flightEntity.getAirline().getAirlineId());
					airline.setAirlineName(flightEntity.getAirline().getAirlineName());
					airline.setAirlineLogo(flightEntity.getAirline().getAirlineLogo().getFileContent());
					airline.setAirlineLogoType(flightEntity.getAirline().getAirlineLogo().getFileType());
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
					flight.setOnSunday(flightEntity.isOnSunday());
					flight.setOnMonday(flightEntity.isOnMonday());
					flight.setOnTuesday(flightEntity.isOnTuesday());
					flight.setOnWednesday(flightEntity.isOnWednesday());
					flight.setOnThursday(flightEntity.isOnThursday());
					flight.setOnFriday(flightEntity.isOnFriday());
					flight.setOnSaturday(flightEntity.isOnSaturday());
					flight.setInstrument(flightEntity.getInstrument());
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
					return flight;
				}).collect(Collectors.toList());
	}

}
