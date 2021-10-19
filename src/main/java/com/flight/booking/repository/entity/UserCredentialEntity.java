package com.flight.booking.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_credentials")
public class UserCredentialEntity implements Serializable {

	private static final long serialVersionUID = -5684896395432089338L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credentials_id")
	private Integer credentialsId;

	@Column(name = "password")
	private String password;

}
