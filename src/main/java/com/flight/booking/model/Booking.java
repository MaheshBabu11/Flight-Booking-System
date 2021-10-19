package com.flight.booking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Booking {

	private Integer bookingId;
	private Roaster roaster;
	private int numberOfPassengers;
	private int price;
	private boolean confirmed;
	private boolean active;
	private int refundAmount;
	private String refundDate;
	private List<Ticket> tickets;
	private User user;
	private Coupon appliedCoupon;

}
