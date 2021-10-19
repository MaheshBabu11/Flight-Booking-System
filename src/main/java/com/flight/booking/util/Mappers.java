package com.flight.booking.util;

import java.util.stream.Collectors;

import com.flight.booking.model.Airline;
import com.flight.booking.model.Booking;
import com.flight.booking.model.Flight;
import com.flight.booking.model.OperatingCity;
import com.flight.booking.model.Pnr;
import com.flight.booking.model.Roaster;
import com.flight.booking.model.RoasterStatus;
import com.flight.booking.model.Ticket;
import com.flight.booking.model.User;
import com.flight.booking.repository.entity.BookingEntity;
import com.flight.booking.repository.entity.PnrEntity;
import com.flight.booking.repository.entity.RoasterEntity;
import com.flight.booking.repository.entity.TicketEntity;
import com.flight.booking.repository.entity.UserEntity;

public class Mappers {

	public static Pnr mapPnrEntityToPnr(PnrEntity pnrEntity) {
		Pnr pnr = new Pnr();
		pnr.setPnrId(pnrEntity.getPnrId());
		pnr.setPnrNumber(pnrEntity.getPnrNumber());
		pnr.setTotalPnrPrice(pnrEntity.getTotalPnrPrice());
		pnr.setActive(true);
		pnr.setConfirmed(true);
		UserEntity bookingUserEntity = pnrEntity.getUser();
		User bookingUser = new User();
		bookingUser.setUserId(bookingUserEntity.getUserId());
		bookingUser.setUserName(bookingUserEntity.getUserName());
		bookingUser.setEmail(bookingUserEntity.getEmail());
		bookingUser.setPhone(bookingUserEntity.getPhone());
		pnr.setUser(bookingUser);
		pnr.setBookings(pnrEntity.getBookings().stream().map(bookingEntity -> mapBookingEntityToBooking(bookingEntity))
				.collect(Collectors.toList()));
		return pnr;
	}

	public static Booking mapBookingEntityToBooking(BookingEntity bookingEntity) {
		Booking booking = new Booking();
		booking.setBookingId(bookingEntity.getBookingId());
		RoasterEntity roasterEntity = bookingEntity.getRoaster();
		Roaster roaster = new Roaster();
		roaster.setRoasterId(roasterEntity.getRoasterId());
		Flight flight = new Flight();
		flight.setFlightId(roasterEntity.getFlight().getFlightId());
		flight.setFlightNumber(roasterEntity.getFlight().getFlightNumber());
		Airline airline = new Airline();
		airline.setAirlineId(roasterEntity.getFlight().getAirline().getAirlineId());
		airline.setAirlineName(roasterEntity.getFlight().getAirline().getAirlineName());
		flight.setAirline(airline);
		roaster.setFlight(flight);
		OperatingCity from = new OperatingCity();
		from.setCityId(roasterEntity.getFrom().getCityId());
		from.setCityCode(roasterEntity.getFrom().getCityCode());
		from.setCityName(roasterEntity.getFrom().getCityName());
		roaster.setFrom(from);
		OperatingCity to = new OperatingCity();
		to.setCityId(roasterEntity.getTo().getCityId());
		to.setCityCode(roasterEntity.getTo().getCityCode());
		to.setCityName(roasterEntity.getTo().getCityName());
		roaster.setTo(to);
		RoasterStatus roasterStatus = new RoasterStatus();
		roasterStatus.setStatusId(roasterEntity.getStatus().getStatusId());
		roasterStatus.setStatusName(roasterEntity.getStatus().getStatusName());
		roaster.setStatus(roasterStatus);
		roaster.setRoasterDate(FlightUtils.getFormattedDate(roasterEntity.getRoasterDate(), "dd/MM/yyyy"));
		roaster.setDepurture(roasterEntity.getDepurture());
		roaster.setArrival(roasterEntity.getArrival());
		booking.setRoaster(roaster);
		booking.setNumberOfPassengers(bookingEntity.getNumberOfPassengers());
		booking.setPrice(bookingEntity.getPrice());
		booking.setConfirmed(bookingEntity.isConfirmed());
		booking.setActive(bookingEntity.isActive());
		booking.setRefundAmount(bookingEntity.getRefundAmount());
		booking.setRefundDate(FlightUtils.getFormattedDate(bookingEntity.getRefundDate(), "dd/MM/yyyy"));
		booking.setTickets(bookingEntity.getTickets().stream()
				.map(ticketEntity -> mapTicketEntityToTicket(ticketEntity)).collect(Collectors.toList()));
		return booking;
	}

	public static Ticket mapTicketEntityToTicket(TicketEntity ticketEntity) {
		Ticket ticket = new Ticket();
		ticket.setTicketId(ticketEntity.getTicketId());
		ticket.setTicketNumber(ticketEntity.getTicketNumber());
		ticket.setPassengerName(ticketEntity.getPassengerName());
		ticket.setPassengerAge(ticketEntity.getPassengerAge());
		ticket.setPassengerContact(ticketEntity.getPassengerContact());
		ticket.setPassengerIdentityType(ticketEntity.getPassengerIdentityType());
		ticket.setSeatType(ticketEntity.getSeatType());
		return ticket;
	}

}
