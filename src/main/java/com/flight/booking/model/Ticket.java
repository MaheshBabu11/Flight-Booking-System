package com.flight.booking.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ticket {

	private Integer ticketId;
	private String ticketNumber;
	private String passengerName;
	private int passengerAge;
	private String passengerContact;
	private String passengerIdentityNumber;
	private String passengerIdentityType;
	private String seatType;
	private Roaster roaster;
	private Booking booking;

}
