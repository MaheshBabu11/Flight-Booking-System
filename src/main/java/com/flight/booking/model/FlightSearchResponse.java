package com.flight.booking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightSearchResponse {

	private String searchType;
	private FlightSearchRequest request;
	private int currentSearch;
	private int nextSearch;
	private List<SelectedRoaster> selectedFlights;
	private List<Roaster> searchResults;

}
