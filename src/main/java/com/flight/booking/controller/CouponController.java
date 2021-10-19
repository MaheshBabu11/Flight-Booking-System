package com.flight.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.booking.model.Coupon;
import com.flight.booking.service.CouponService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("flight")
@CrossOrigin
@Api(tags = { "Coupon" }, value = "coupon")
public class CouponController {

	@Autowired
	private CouponService couponService;

	@GetMapping("all-coupons")
	@ApiOperation(value = "all-coupons", nickname = "all-coupons", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = Coupon.class, responseContainer = "List") })
	public List<Coupon> getAllCoupons(
			@RequestParam(name = "include-inactive", defaultValue = "false") String includeInactive) {
		return couponService.getAllCoupons(Boolean.parseBoolean(includeInactive));
	}

	@GetMapping("get-by-coupon-name")
	@ApiOperation(value = "get-by-coupon-name", nickname = "get-by-coupon-name", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = Coupon.class, responseContainer = "List") })
	public List<Coupon> getByCouponName(@RequestParam(name = "name", required = true) String couponName) {
		return couponService.getByCouponName(couponName);
	}

	@PostMapping("add-coupon")
	@ApiOperation(value = "add-coupon", nickname = "add-coupon", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = Coupon.class, responseContainer = "List") })
	public List<Coupon> addCoupons(@RequestBody List<Coupon> coupons) {
		return couponService.addCoupons(coupons);
	}

	@PutMapping("change-coupon-status")
	@ApiOperation(value = "change-coupon-status", nickname = "change-coupon-status", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = Integer.class) })
	public Integer changeCouponStatus(@RequestParam(name = "couponId", required = true) String couponId,
			@RequestParam(name = "status", required = true) String status) {
		return couponService.changeCouponStatus(Integer.parseInt(couponId), Boolean.parseBoolean(status));
	}

}
