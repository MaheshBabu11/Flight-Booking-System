package com.flight.booking.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.booking.model.Booking;
import com.flight.booking.model.Pnr;
import com.flight.booking.model.Ticket;
import com.flight.booking.model.TicketDetails;
import com.flight.booking.model.User;
import com.flight.booking.repository.CouponRepository;
import com.flight.booking.repository.PnrRepository;
import com.flight.booking.repository.RoasterRepository;
import com.flight.booking.repository.TicketRepository;
import com.flight.booking.repository.UserRepository;
import com.flight.booking.repository.entity.BookingEntity;
import com.flight.booking.repository.entity.PnrEntity;
import com.flight.booking.repository.entity.RoasterEntity;
import com.flight.booking.repository.entity.TicketEntity;
import com.flight.booking.repository.entity.UserEntity;
import com.flight.booking.util.FlightUtils;
import com.flight.booking.util.Mappers;

import net.sf.jasperreports.engine.JRException;

@Service
public class FlightBookingService {

	@Autowired
	private PnrRepository pnrRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoasterRepository roasterRepository;

	@Autowired
	private CouponRepository couponRepository;

	public Pnr bookPnr(Pnr pnr) {
		List<Booking> bookings = pnr.getBookings();
		User bookingUser = pnr.getUser();
		UserEntity userEntity = userRepository.findByEmail(bookingUser.getEmail()).get();
		PnrEntity pnrEntity = new PnrEntity();
		String pnrNumber = FlightUtils.generatePnrNumber();
		pnrEntity.setPnrNumber(pnrNumber);
		pnrEntity.setUser(userEntity);
		List<BookingEntity> bookingEntities = new ArrayList<>();
		int totalPnrPrice = 0;
		for (Booking booking : bookings) {
			BookingEntity bookingEntity = new BookingEntity();
			bookingEntity.setNumberOfPassengers(booking.getNumberOfPassengers());
			bookingEntity.setPrice(booking.getPrice());
			totalPnrPrice = totalPnrPrice + booking.getPrice();
			bookingEntity.setConfirmed(true);
			bookingEntity.setActive(true);
			bookingEntity.setPnr(pnrEntity);
			RoasterEntity roasterEntity = roasterRepository.findById(booking.getRoaster().getRoasterId()).get();
			bookingEntity.setRoaster(roasterEntity);
			if (booking.getAppliedCoupon() != null && booking.getAppliedCoupon().getCouponId() != null) {
				bookingEntity
						.setAppliedCoupon(couponRepository.findById(booking.getAppliedCoupon().getCouponId()).get());
			}
			bookingEntity.setUser(userEntity);
			bookingEntity.setPnr(pnrEntity);
			List<TicketEntity> ticketEntities = new ArrayList<>();
			for (Ticket ticket : booking.getTickets()) {
				TicketEntity ticketEntity = new TicketEntity();
				String ticketNumber = FlightUtils.generateTicketNumber();
				ticketEntity.setTicketNumber(ticketNumber);
				ticketEntity.setPassengerName(ticket.getPassengerName());
				ticketEntity.setPassengerAge(ticket.getPassengerAge());
				ticketEntity.setPassengerContact(ticket.getPassengerContact());
				ticketEntity.setPassengerIdentityType(ticket.getPassengerIdentityType());
				ticketEntity.setPassengerIdentityNumber(ticket.getPassengerIdentityNumber());
				ticketEntity.setSeatType(ticket.getSeatType());
				ticketEntity.setBooking(bookingEntity);
				ticketEntities.add(ticketEntity);
			}
			bookingEntity.setTickets(ticketEntities);
			bookingEntities.add(bookingEntity);
		}
		pnrEntity.setBookings(bookingEntities);
		pnrEntity.setTotalPnrPrice(totalPnrPrice);
		pnrEntity = pnrRepository.save(pnrEntity);
		for (BookingEntity bEntity : pnrEntity.getBookings()) {
			int numberOfBusinessSeat = 0;
			int numberOfEconomySeat = 0;
			for (TicketEntity tEntity : bEntity.getTickets()) {
				if (tEntity.getSeatType().equalsIgnoreCase("BUSINESS")) {
					numberOfBusinessSeat++;
				} else {
					numberOfEconomySeat++;
				}
			}
			if (numberOfBusinessSeat > 0) {
				updateRoasterSeatCount(bEntity.getRoaster().getRoasterId(), "business", "remove", numberOfBusinessSeat);
			}
			if (numberOfEconomySeat > 0) {
				updateRoasterSeatCount(bEntity.getRoaster().getRoasterId(), "economy", "remove", numberOfEconomySeat);
			}
		}
		pnr = Mappers.mapPnrEntityToPnr(pnrEntity);
		return pnr;
	}

	public int updateRoasterSeatCount(int roasterId, String seatType, String operationType, int numberOfSeats) {
		RoasterEntity roasterEntity = roasterRepository.findById(roasterId).get();
		if (seatType.equals("business")) {
			if (operationType.equals("add")) {
				roasterEntity
						.setBusinessClassSeatsAvailable(roasterEntity.getBusinessClassSeatsAvailable() + numberOfSeats);
			} else {
				roasterEntity
						.setBusinessClassSeatsAvailable(roasterEntity.getBusinessClassSeatsAvailable() - numberOfSeats);
			}
		} else {
			if (operationType.equals("add")) {
				roasterEntity.setNonBusinessClassSeatsAvailable(
						roasterEntity.getNonBusinessClassSeatsAvailable() + numberOfSeats);
			} else {
				roasterEntity.setNonBusinessClassSeatsAvailable(
						roasterEntity.getNonBusinessClassSeatsAvailable() - numberOfSeats);
			}
		}
		roasterRepository.save(roasterEntity);
		return roasterId;
	}

	public Pnr searchPnr(String pnrNumber) {
		return Optional.ofNullable(pnrRepository.findByPnrNumber(pnrNumber).orElse(null))
				.map(pnrEntity -> Mappers.mapPnrEntityToPnr(pnrEntity)).orElse(null);
	}

	public List<Pnr> searchPnrByUser(String email) {
		return Optional
				.ofNullable(userRepository.findByEmail(email).orElse(null)).map(userEntity -> userEntity.getPnrs()
						.stream().map(pnrEntity -> Mappers.mapPnrEntityToPnr(pnrEntity)).collect(Collectors.toList()))
				.get();
	}

	public TicketDetails searchTicket(String ticketNumber) {
		return Optional.ofNullable(ticketRepository.findByTicketNumber(ticketNumber).orElse(null)).map(ticketEntity -> {
			TicketDetails ticketDetails = new TicketDetails();
			BookingEntity bookingEntity = ticketEntity.getBooking();
			PnrEntity pnrEntity = bookingEntity.getPnr();
			ticketDetails.setTicketId(ticketEntity.getTicketId());
			ticketDetails.setTicketNumber(ticketEntity.getTicketNumber());
			ticketDetails.setPnrId(pnrEntity.getPnrId());
			ticketDetails.setBookingActive(bookingEntity.isActive());
			ticketDetails.setPassengerName(ticketEntity.getPassengerName());
			ticketDetails.setPassengerAge(ticketEntity.getPassengerAge());
			ticketDetails.setPassengerContact(ticketEntity.getPassengerContact());
			ticketDetails.setPassengerIdentityType(ticketEntity.getPassengerIdentityType());
			ticketDetails.setSeatType(ticketEntity.getSeatType());
			ticketDetails.setPnrNumber(pnrEntity.getPnrNumber());
			ticketDetails.setBookingConfirmed(bookingEntity.isConfirmed());
			ticketDetails.setFlightNumber(bookingEntity.getRoaster().getFlight().getFlightNumber());
			ticketDetails.setAirline(bookingEntity.getRoaster().getFlight().getAirline().getAirlineName());
			ticketDetails.setFromCityCode(bookingEntity.getRoaster().getFrom().getCityCode());
			ticketDetails.setFromCityName(bookingEntity.getRoaster().getFrom().getCityName());
			ticketDetails.setToCityCode(bookingEntity.getRoaster().getTo().getCityCode());
			ticketDetails.setToCityName(bookingEntity.getRoaster().getTo().getCityName());
			ticketDetails.setRoasterStatus(bookingEntity.getRoaster().getStatus().getStatusName());
			ticketDetails.setRoasterDate(
					FlightUtils.getFormattedDate(bookingEntity.getRoaster().getRoasterDate(), "dd/MM/yyyy"));
			ticketDetails.setDepurture(bookingEntity.getRoaster().getDepurture());
			ticketDetails.setArrival(bookingEntity.getRoaster().getArrival());
			ticketDetails.setTicketPrice((bookingEntity.getPrice() / bookingEntity.getNumberOfPassengers()));
			if (bookingEntity.getAppliedCoupon() != null) {
				ticketDetails.setCouponId(bookingEntity.getAppliedCoupon().getCouponId());
				ticketDetails.setCouponName(bookingEntity.getAppliedCoupon().getCouponName());
				ticketDetails.setCouponDescription(bookingEntity.getAppliedCoupon().getCouponDescription());
			}
			ticketDetails.setBookingUserName(bookingEntity.getUser().getUserName());
			ticketDetails.setBookingUserEmail(bookingEntity.getUser().getEmail());
			return ticketDetails;
		}).orElse(null);
	}

	public String downloadTicket(String ticketNumber) {
		TicketDetails ticketDetails = searchTicket(ticketNumber);
		byte[] pdf = null;
		if (ticketDetails != null) {
			try {
				pdf = FlightUtils.createTicketPdf(Arrays.asList(ticketDetails));
			} catch (JRException e) {
				e.printStackTrace();
				throw new RuntimeException("not able to download ticket");
			}
		}
		return Base64.getEncoder().encodeToString(pdf);
	}

}
