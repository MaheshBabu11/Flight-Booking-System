package com.flight.booking.repository.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roaster_status")
public class RoasterStatusEntity implements Serializable {

	private static final long serialVersionUID = -4335453650448142371L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id")
	private Integer statusId;

	@Column(name = "status_name")
	private String statusName;

	@OneToMany(mappedBy = "status")
	private List<RoasterEntity> roasters;

}
