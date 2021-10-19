package com.flight.booking.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "flights")
public class FlightEntity implements Serializable {

	private static final long serialVersionUID = -6889116364259577806L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_id")
	private Integer flightId;

	@Column(name = "flight_number")
	private String flightNumber;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "duration")
	private String duration;

	@Column(name = "on_sunday")
	private boolean onSunday;

	@Column(name = "on_monday")
	private boolean onMonday;

	@Column(name = "on_tuesday")
	private boolean onTuesday;

	@Column(name = "on_wednesday")
	private boolean onWednesday;

	@Column(name = "on_thursday")
	private boolean onThursday;

	@Column(name = "on_friday")
	private boolean onFriday;

	@Column(name = "on_saturday")
	private boolean onSaturday;

	@Column(name = "instrument")
	private String instrument;

	@Column(name = "business_class_seats")
	private int businessClassSeats;

	@Column(name = "non_business_class_seats")
	private int nonBusinessClassSeats;

	@Column(name = "business_class_seats_price")
	private int businessClassSeatsPrice;

	@Column(name = "non_business_class_seat_price")
	private int nonBusinessClassSeatsPrice;

	@Column(name = "row_count")
	private int rowCount;

	@Column(name = "column_count")
	private int columnCount;

	@Column(name = "active")
	private boolean active;

	@Column(name = "updated_by")
	@LastModifiedBy
	private String updatedBy;

	@Column(name = "updated_on")
	@LastModifiedDate
	private Timestamp updatedOn;

	@ManyToOne
	@JoinColumn(name = "airline", referencedColumnName = "airline_id")
	private AirlineEntity airline;

	@ManyToOne
	@JoinColumn(name = "from_city", referencedColumnName = "city_id")
	private OperatingCityEntity from;

	@ManyToOne
	@JoinColumn(name = "to_city", referencedColumnName = "city_id")
	private OperatingCityEntity to;

	@ManyToOne
	@JoinColumn(name = "meal", referencedColumnName = "meal_id")
	private MealEntity meal;

	@OneToMany(mappedBy = "flight")
	private List<RoasterEntity> roasters;

}
