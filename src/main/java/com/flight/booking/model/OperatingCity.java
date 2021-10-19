package com.flight.booking.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperatingCity {

	private Integer cityId;
	private String cityCode;
	private String cityName;
	private boolean active;
	private String updatedBy;
	private String updatedOn;

}
