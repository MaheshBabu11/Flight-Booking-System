package com.flight.booking.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "roaster")
public class RoasterEntity implements Serializable {

	private static final long serialVersionUID = 2143083037562687695L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roaster_id")
	private Integer roasterId;

	@Column(name = "roaster_date")
	@Temporal(TemporalType.DATE)
	private Date roasterDate;

	@Column(name = "depurture")
	private String depurture;

	@Column(name = "arrival")
	private String arrival;

	@Column(name = "delay_time_in_mins")
	private int delayTimeInMins;

	@Column(name = "business_class_seats_available")
	private int businessClassSeatsAvailable;

	@Column(name = "non_business_class_seats_available")
	private int nonBusinessClassSeatsAvailable;

	@Column(name = "business_class_seat_price")
	private int businessClassSeatsPrice;

	@Column(name = "non_business_class_seat_price")
	private int nonBusinessClassSeatsPrice;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "updated_by")
	@LastModifiedBy
	private String updatedBy;

	@Column(name = "updated_on")
	@LastModifiedDate
	private Timestamp updatedOn;

	@ManyToOne
	@JoinColumn(name = "flight", referencedColumnName = "flight_id")
	private FlightEntity flight;

	@ManyToOne
	@JoinColumn(name = "from_city", referencedColumnName = "city_id")
	private OperatingCityEntity from;

	@ManyToOne
	@JoinColumn(name = "to_city", referencedColumnName = "city_id")
	private OperatingCityEntity to;

	@ManyToOne
	@JoinColumn(name = "status", referencedColumnName = "status_id")
	private RoasterStatusEntity status;

	@OneToMany(mappedBy = "roaster")
	private List<BookingEntity> bookings;

}
