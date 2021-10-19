package com.flight.booking.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchCritaria {

	private String originCityCode;
	private String destinationCityCode;
	private String journeyDate;

}
