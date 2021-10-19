package com.flight.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.booking.repository.entity.CouponsEntity;

public interface CouponRepository extends JpaRepository<CouponsEntity, Integer> {

	List<CouponsEntity> findByCouponName(String couponName);

}
