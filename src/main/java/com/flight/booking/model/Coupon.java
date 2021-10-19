package com.flight.booking.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coupon {

	private Integer couponId;
	private String couponName;
	private boolean discountCoupon;
	private String couponDescription;
	private int couponDiscountPercentage;
	private boolean active;
	private String updatedBy;
	private String updatedOn;

}
