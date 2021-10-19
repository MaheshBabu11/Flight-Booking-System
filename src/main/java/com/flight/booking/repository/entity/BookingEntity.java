package com.flight.booking.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class BookingEntity implements Serializable {

	private static final long serialVersionUID = -8635694158270551717L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Integer bookingId;

	@Column(name = "number_of_passengers")
	private int numberOfPassengers;

	@Column(name = "price")
	private int price;

	@Column(name = "confirmed")
	private boolean confirmed;

	@Column(name = "active")
	private boolean active;

	@Column(name = "refund_amount")
	private int refundAmount;

	@Column(name = "refund_date")
	private Timestamp refundDate;

	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "user_id")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "applied_coupon", referencedColumnName = "coupon_id")
	private CouponsEntity appliedCoupon;

	@ManyToOne
	@JoinColumn(name = "pnr", referencedColumnName = "pnr_id")
	private PnrEntity pnr;

	@ManyToOne
	@JoinColumn(name = "roaster", referencedColumnName = "roaster_id")
	private RoasterEntity roaster;

	@OneToMany(mappedBy = "booking", cascade = CascadeType.PERSIST)
	private List<TicketEntity> tickets;

}
