package com.flight.booking.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.flight.booking.model.OperatingCity;
import com.flight.booking.repository.entity.FlightEntity;
import com.flight.booking.repository.entity.RoasterEntity;
import com.flight.booking.repository.entity.RoasterStatusEntity;
import com.flight.booking.util.FlightUtils;

@Repository
public class CustomJpaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<FlightEntity> findFlightByDate(String date) {
		String day = FlightUtils.getDayOfDate(date, "dd/MM/yyyy");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<FlightEntity> query = cb.createQuery(FlightEntity.class);
		Root<FlightEntity> root = query.from(FlightEntity.class);
		Expression<Boolean> daypath = null;
		switch (day.toLowerCase()) {
		case "sunday":
			daypath = root.get("onSunday");
			break;
		case "monday":
			daypath = root.get("onMonday");
			break;
		case "tuesday":
			daypath = root.get("onTuesday");
			break;
		case "wednesday":
			daypath = root.get("onWednesday");
			break;
		case "thursday":
			daypath = root.get("onThursday");
			break;
		case "friday":
			daypath = root.get("onFriday");
			break;
		case "saturday":
			daypath = root.get("onSaturday");
			break;
		}
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(cb.equal(daypath, true));
		query.select(root).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		return entityManager.createQuery(query).getResultList();
	}

	public List<RoasterEntity> findRoasterByOriginDestinationJourneyDate(int originCityId, int destinationCityId,
			Date journeyDate) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RoasterEntity> query = cb.createQuery(RoasterEntity.class);
		Root<RoasterEntity> root = query.from(RoasterEntity.class);
		Expression<OperatingCity> originCitypath = root.get("from");
		Expression<OperatingCity> destinationCitypath = root.get("to");
		Expression<Date> journeyDatepath = root.get("roasterDate");
		Expression<RoasterStatusEntity> statuspath = root.get("status");
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(cb.equal(originCitypath, originCityId));
		predicates.add(cb.equal(destinationCitypath, destinationCityId));
		predicates.add(cb.equal(journeyDatepath, journeyDate));
		predicates.add(cb.equal(statuspath, 1));
		query.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		return entityManager.createQuery(query).getResultList();
	}

}
