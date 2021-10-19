package com.flight.booking.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Flight {

	private Integer flightId;
	private String flightNumber;
	private Airline airline;
	private OperatingCity from;
	private OperatingCity to;
	private String startTime;
	private String endTime;
	private String duration;
	private boolean onSunday;
	private boolean onMonday;
	private boolean onTuesday;
	private boolean onWednesday;
	private boolean onThursday;
	private boolean onFriday;
	private boolean onSaturday;
	private String instrument;
	private int businessClassSeats;
	private int nonBusinessClassSeats;
	private int businessClassSeatsPrice;
	private int nonBusinessClassSeatsPrice;
	private int rowCount;
	private int columnCount;
	private boolean active;
	private Meal meal;
	private String updatedBy;
	private String updatedOn;

}
