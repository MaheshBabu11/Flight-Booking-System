package com.flight.booking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Roaster {

	private Integer roasterId;
	private Flight flight;
	private OperatingCity from;
	private OperatingCity to;
	private RoasterStatus status;
	private List<Booking> bookings;
	private String roasterDate;
	private String depurture;
	private String arrival;
	private int delayTimeInMins;
	private int businessClassSeatsAvailable;
	private int nonBusinessClassSeatsAvailable;
	private int businessClassSeatsPrice;
	private int nonBusinessClassSeatsPrice;
	private String remarks;
	private String updatedBy;
	private String updatedOn;

}
