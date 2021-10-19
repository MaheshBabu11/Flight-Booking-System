package com.flight.booking.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TicketDetails {

	private Integer ticketId;
	private String ticketNumber;
	private Integer pnrId;
	private String pnrNumber;
	private String passengerName;
	private int passengerAge;
	private String passengerContact;
	private String passengerIdentityNumber;
	private String passengerIdentityType;
	private String seatType;
	private String flightNumber;
	private String airline;
	private String fromCityCode;
	private String fromCityName;
	private String toCityCode;
	private String toCityName;
	private String roasterStatus;
	private String roasterDate;
	private String depurture;
	private String arrival;
	private int ticketPrice;
	private Integer couponId;
	private String couponName;
	private String couponDescription;
	private boolean bookingConfirmed;
	private boolean bookingActive;
	private String bookingUserName;
	private String bookingUserEmail;

}
