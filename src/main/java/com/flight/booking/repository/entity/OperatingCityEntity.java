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
@Table(name = "operating_cities")
public class OperatingCityEntity implements Serializable {

	private static final long serialVersionUID = 8213113794622937798L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private Integer cityId;

	@Column(name = "city_code")
	private String cityCode;

	@Column(name = "city_name")
	private String cityName;

	@Column(name = "active")
	private boolean active;

	@Column(name = "updated_by")
	@LastModifiedBy
	private String updatedBy;

	@Column(name = "updated_on")
	@LastModifiedDate
	private Timestamp updatedOn;

	@OneToMany(mappedBy = "from")
	private List<FlightEntity> opeartingFlights;

	@OneToMany(mappedBy = "from")
	private List<RoasterEntity> roasters;

}
