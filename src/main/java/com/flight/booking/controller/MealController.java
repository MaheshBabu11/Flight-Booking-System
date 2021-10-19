package com.flight.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.booking.model.Meal;
import com.flight.booking.service.MealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("flight")
@CrossOrigin
@Api(tags = { "Meal" }, value = "meal")
public class MealController {

	@Autowired
	private MealService mealService;

	@GetMapping("all-meals")
	@ApiOperation(value = "all-meals", nickname = "all-meals", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = Meal.class, responseContainer = "List") })
	public List<Meal> getAllMeals() {
		return mealService.getAllMeals();
	}

}
