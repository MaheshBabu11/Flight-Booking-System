package com.flight.booking.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class TicketEntity implements Serializable {

	private static final long serialVersionUID = 2097166703072681544L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private Integer ticketId;

	@Column(name = "ticket_number")
	private String ticketNumber;

	@Column(name = "passenger_name")
	private String passengerName;

	@Column(name = "passenger_age")
	private int passengerAge;

	@Column(name = "passenger_contact")
	private String passengerContact;

	@Column(name = "passenger_identity_number")
	private String passengerIdentityNumber;

	@Column(name = "passenger_identity_type")
	private String passengerIdentityType;

	@Column(name = "seat_type")
	private String seatType;

	@ManyToOne
	@JoinColumn(name = "booking", referencedColumnName = "booking_id")
	private BookingEntity booking;

}
