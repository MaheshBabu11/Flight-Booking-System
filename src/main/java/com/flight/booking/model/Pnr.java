package com.flight.booking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pnr {

	private Integer pnrId;
	private String pnrNumber;
	private int totalPnrPrice;
	private boolean confirmed;
	private boolean active;
	private User user;
	private List<Booking> bookings;

}
