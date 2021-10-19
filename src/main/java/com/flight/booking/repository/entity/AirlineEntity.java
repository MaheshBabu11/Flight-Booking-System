package com.flight.booking.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "airlines")
public class AirlineEntity implements Serializable {

	private static final long serialVersionUID = -2243708584920456129L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "airline_id")
	private Integer airlineId;

	@Column(name = "airline_name")
	private String airlineName;

	@Column(name = "airline_contact_number")
	private String airlineContactNumber;

	@Column(name = "airline_address")
	private String airlineAddress;

	@Column(name = "active")
	private boolean active;

	@Column(name = "updated_by")
	@LastModifiedBy
	private String updatedBy;

	@Column(name = "updated_on")
	@LastModifiedDate
	private Timestamp updatedOn;

	@OneToOne
	@JoinColumn(name = "airline_logo", referencedColumnName = "file_id")
	private FilesEntity airlineLogo;

	@OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
	private List<FlightEntity> flights;

}
