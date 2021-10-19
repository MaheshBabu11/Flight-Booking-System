package com.flight.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.booking.model.Airline;
import com.flight.booking.service.AirlineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("flight")
@CrossOrigin
@Api(tags = { "Airline" }, value = "airline")
public class AirlineController {

	@Autowired
	private AirlineService airlineService;

	@PostMapping("add-airline")
	@ApiOperation(value = "add-airline", nickname = "add-airline", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = Airline.class, responseContainer = "List") })
	public List<Airline> addAirlines(@RequestBody List<Airline> airlines) {
		return airlineService.addAirlines(airlines);
	}

	@GetMapping("all-airlines")
	@ApiOperation(value = "all-airlines", nickname = "all-airlines", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = Airline.class, responseContainer = "List") })
	public List<Airline> getAllAirlines(
			@RequestParam(name = "include-inactive", defaultValue = "false") String includeInactive) {
		return airlineService.getAllAirlines(Boolean.parseBoolean(includeInactive));
	}

	@PutMapping("update-airlines")
	@ApiOperation(value = "update-airlines", nickname = "update-airlines", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = Airline.class, responseContainer = "List") })
	public List<Airline> updateAirlines(@RequestBody List<Airline> airlines) {
		return airlineService.updateAirlines(airlines);
	}

	@PutMapping("change-airline-status")
	@ApiOperation(value = "change-airline-status", nickname = "change-airline-status", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = Integer.class) })
	public Integer changeAirlineStatus(@RequestParam(name = "airlineId", required = true) String airlineId,
			@RequestParam(name = "status", required = true) String status) {
		return airlineService.changeAirlineStatus(Integer.parseInt(airlineId), Boolean.parseBoolean(status));
	}

	@DeleteMapping("delete-airlines")
	@ApiOperation(value = "delete-airlines", nickname = "delete-airlines", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = Integer.class, responseContainer = "List") })
	public List<Integer> deleteAirlines(@RequestParam List<Integer> airlineIds) {
		return airlineService.deleteAirlines(airlineIds);
	}

}
