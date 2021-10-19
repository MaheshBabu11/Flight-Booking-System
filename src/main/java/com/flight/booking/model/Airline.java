package com.flight.booking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Airline {

	private Integer airlineId;
	private String airlineName;
	private String airlineContactNumber;
	private String airlineAddress;
	private boolean active;
	private String updatedBy;
	private String updatedOn;
	private byte[] airlineLogo;
	private Integer airlineLogoId;
	private String airlineLogoType;
	private List<Flight> flights;

}
