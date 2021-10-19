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
@Table(name = "pnrs")
public class PnrEntity implements Serializable {

	private static final long serialVersionUID = -8635694158270551717L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pnr_id")
	private Integer pnrId;

	@Column(name = "pnr_number")
	private String pnrNumber;

	@Column(name="total_pnr_price")
	private int totalPnrPrice;

	@Column(name = "updated_by")
	@LastModifiedBy
	private String updatedBy;

	@Column(name = "updated_on")
	@LastModifiedDate
	private Timestamp updatedOn;

	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "user_id")
	private UserEntity user;

	@OneToMany(mappedBy = "pnr", cascade = CascadeType.PERSIST)
	private List<BookingEntity> bookings;

}
