package com.flight.booking.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.booking.model.Airline;
import com.flight.booking.model.Flight;
import com.flight.booking.model.FlightSearchRequest;
import com.flight.booking.model.FlightSearchResponse;
import com.flight.booking.model.Meal;
import com.flight.booking.model.OperatingCity;
import com.flight.booking.model.Roaster;
import com.flight.booking.model.RoasterStatus;
import com.flight.booking.model.SearchCritaria;
import com.flight.booking.model.SelectedRoaster;
import com.flight.booking.repository.CustomJpaRepository;
import com.flight.booking.repository.OperatingCityRepository;
import com.flight.booking.repository.RoasterRepository;
import com.flight.booking.repository.entity.FlightEntity;
import com.flight.booking.repository.entity.RoasterEntity;
import com.flight.booking.util.FlightUtils;

@Service
public class FlightSearchService {

	@Autowired
	private RoasterRepository roasterRepository;

	@Autowired
	private CustomJpaRepository customJpaRepository;

	@Autowired
	private OperatingCityRepository cityRepository;

	public FlightSearchResponse searchFlights(FlightSearchRequest searchRq) {
		FlightSearchResponse flightSearchResponse = new FlightSearchResponse();
		if (searchRq.getCurrentSearch() != 0) {
			SearchCritaria critaria = searchRq.getSearchCriterias().get(searchRq.getCurrentSearch() - 1);
			Date journeyDate = FlightUtils.getFormattedDate(critaria.getJourneyDate(), "dd/MM/yyyy");
			int originCityId = cityRepository.findByCityCode(critaria.getOriginCityCode()).get().getCityId();
			int destinationCityId = cityRepository.findByCityCode(critaria.getDestinationCityCode()).get().getCityId();
			List<RoasterEntity> roasterEntities = customJpaRepository
					.findRoasterByOriginDestinationJourneyDate(originCityId, destinationCityId, journeyDate);
			List<Roaster> roasters = roasterEntities.parallelStream().map(roasterEntity -> {
				Roaster roaster = new Roaster();
				roaster.setRoasterId(roasterEntity.getRoasterId());
				FlightEntity roasterFlightEntity = roasterEntity.getFlight();
				Flight flight = new Flight();
				flight.setFlightId(roasterFlightEntity.getFlightId());
				flight.setFlightNumber(roasterFlightEntity.getFlightNumber());
				Airline airline = new Airline();
				airline.setAirlineId(roasterFlightEntity.getAirline().getAirlineId());
				airline.setAirlineName(roasterFlightEntity.getAirline().getAirlineName());
				flight.setAirline(airline);
				flight.setStartTime(roasterFlightEntity.getStartTime());
				flight.setEndTime(roasterFlightEntity.getEndTime());
				flight.setDuration(roasterFlightEntity.getDuration());
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
				roaster.setRoasterDate(new SimpleDateFormat("dd/MM/yyyy").format(roasterEntity.getRoasterDate()));
				roaster.setDepurture(roasterEntity.getDepurture());
				roaster.setArrival(roasterEntity.getArrival());
				roaster.setDelayTimeInMins(roasterEntity.getDelayTimeInMins());
				roaster.setBusinessClassSeatsAvailable(roasterEntity.getBusinessClassSeatsAvailable());
				roaster.setNonBusinessClassSeatsAvailable(roasterEntity.getNonBusinessClassSeatsAvailable());
				roaster.setBusinessClassSeatsPrice(roasterEntity.getBusinessClassSeatsPrice());
				roaster.setNonBusinessClassSeatsPrice(roasterEntity.getNonBusinessClassSeatsPrice());
				roaster.setRemarks(roasterEntity.getRemarks());
				return roaster;
			}).collect(Collectors.toList());
			flightSearchResponse.setSearchResults(roasters);
		}
		flightSearchResponse.setSearchType(searchRq.getSearchType());
		flightSearchResponse.setRequest(searchRq);
		flightSearchResponse.setCurrentSearch(searchRq.getCurrentSearch());
		if (searchRq.getCurrentSearch() == 0 || searchRq.getCurrentSearch() == searchRq.getSearchCriterias().size()) {
			flightSearchResponse.setNextSearch(0);
		} else {
			flightSearchResponse.setNextSearch(searchRq.getCurrentSearch() + 1);
		}
		if (searchRq.getSelectedFlights() != null && !searchRq.getSelectedFlights().isEmpty()) {
			List<SelectedRoaster> selectedRoasters = searchRq.getSelectedFlights().stream().map(selectedRoaster -> {
				RoasterEntity roasterEntity = roasterRepository.findById(selectedRoaster.getRoasterId()).get();
				Roaster roaster = new Roaster();
				roaster.setRoasterId(roasterEntity.getRoasterId());
				FlightEntity roasterFlightEntity = roasterEntity.getFlight();
				Flight flight = new Flight();
				flight.setFlightId(roasterFlightEntity.getFlightId());
				flight.setFlightNumber(roasterFlightEntity.getFlightNumber());
				Airline airline = new Airline();
				airline.setAirlineId(roasterFlightEntity.getAirline().getAirlineId());
				airline.setAirlineName(roasterFlightEntity.getAirline().getAirlineName());
				flight.setAirline(airline);
				flight.setStartTime(roasterFlightEntity.getStartTime());
				flight.setEndTime(roasterFlightEntity.getEndTime());
				flight.setDuration(roasterFlightEntity.getDuration());
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
				roaster.setRoasterDate(new SimpleDateFormat("dd/MM/yyyy").format(roasterEntity.getRoasterDate()));
				roaster.setDepurture(roasterEntity.getDepurture());
				roaster.setArrival(roasterEntity.getArrival());
				roaster.setDelayTimeInMins(roasterEntity.getDelayTimeInMins());
				roaster.setBusinessClassSeatsAvailable(roasterEntity.getBusinessClassSeatsAvailable());
				roaster.setNonBusinessClassSeatsAvailable(roasterEntity.getNonBusinessClassSeatsAvailable());
				roaster.setBusinessClassSeatsPrice(roasterEntity.getBusinessClassSeatsPrice());
				roaster.setNonBusinessClassSeatsPrice(roasterEntity.getNonBusinessClassSeatsPrice());
				roaster.setRemarks(roasterEntity.getRemarks());
				selectedRoaster.setRoaster(roaster);
				return selectedRoaster;
			}).collect(Collectors.toList());
			flightSearchResponse.setSelectedFlights(selectedRoasters);
		}
		return flightSearchResponse;
	}

}
