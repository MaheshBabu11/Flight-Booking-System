package com.flight.booking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightSearchRequest {

	private String searchType;
	private int currentSearch;
	private List<SelectedRoaster> selectedFlights;
	private List<SearchCritaria> searchCriterias;

}
