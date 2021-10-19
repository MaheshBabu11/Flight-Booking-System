package com.flight.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.booking.model.FlightSearchRequest;
import com.flight.booking.model.FlightSearchResponse;
import com.flight.booking.service.FlightSearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("flight")
@CrossOrigin
@Api(tags = { "Flight search" }, value = "flight search")
public class FlightSearchController {

	@Autowired
	private FlightSearchService flightSearchService;

	@PostMapping("search")
	@ApiOperation(value = "search", nickname = "search", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = FlightSearchResponse.class) })
	public FlightSearchResponse getAllFlights(@RequestBody FlightSearchRequest searchRq) {
		return flightSearchService.searchFlights(searchRq);
	}

}
