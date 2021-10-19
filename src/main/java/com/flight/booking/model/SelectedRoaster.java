package com.flight.booking.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectedRoaster {

	private Integer roasterId;
	private String seatType;
	private String seatPrice;
	private Roaster roaster;

}
