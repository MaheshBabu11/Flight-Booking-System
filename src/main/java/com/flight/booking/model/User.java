package com.flight.booking.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

	private Integer userId;
	private String userName;
	private String email;
	private String phone;
	private boolean emailVerified;
	private String role;

}
