package com.flight.booking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.booking.model.Meal;
import com.flight.booking.repository.MealRepository;

@Service
public class MealService {

	@Autowired
	private MealRepository mealRepository;

	public List<Meal> getAllMeals() {
		return mealRepository.findAll().parallelStream().map(mealEntity -> {
			Meal meal = new Meal();
			meal.setMealId(mealEntity.getMealId());
			meal.setMealType(mealEntity.getMealType());
			meal.setMealDescription(mealEntity.getMealDescription());
			return meal;
		}).collect(Collectors.toList());
	}

}
