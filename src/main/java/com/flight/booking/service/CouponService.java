package com.flight.booking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.booking.model.Coupon;
import com.flight.booking.repository.CouponRepository;
import com.flight.booking.repository.entity.CouponsEntity;

@Service
public class CouponService {

	@Autowired
	private CouponRepository couponRepository;

	public List<Coupon> getAllCoupons(boolean includeInactive) {
		return couponRepository.findAll().parallelStream()
				.filter(couponsEntity -> includeInactive ? true : couponsEntity.isActive()).map(couponsEntity -> {
					Coupon coupon = new Coupon();
					coupon.setCouponId(couponsEntity.getCouponId());
					coupon.setCouponName(couponsEntity.getCouponName());
					coupon.setCouponDescription(couponsEntity.getCouponDescription());
					coupon.setDiscountCoupon(couponsEntity.isDiscountCoupon());
					coupon.setCouponDiscountPercentage(couponsEntity.getCouponDiscountPercentage());
					coupon.setActive(couponsEntity.isActive());
					return coupon;
				}).collect(Collectors.toList());
	}

	public List<Coupon> getByCouponName(String couponName) {
		return couponRepository.findByCouponName(couponName).parallelStream().map(couponsEntity -> {
			Coupon coupon = new Coupon();
			coupon.setCouponId(couponsEntity.getCouponId());
			coupon.setCouponName(couponsEntity.getCouponName());
			coupon.setCouponDescription(couponsEntity.getCouponDescription());
			coupon.setDiscountCoupon(couponsEntity.isDiscountCoupon());
			coupon.setCouponDiscountPercentage(couponsEntity.getCouponDiscountPercentage());
			coupon.setActive(couponsEntity.isActive());
			return coupon;
		}).collect(Collectors.toList());
	}

	public List<Coupon> addCoupons(List<Coupon> coupons) {
		return coupons.parallelStream().map(coupon -> {
			CouponsEntity couponsEntity = new CouponsEntity();
			couponsEntity.setCouponName(coupon.getCouponName());
			couponsEntity.setCouponDescription(coupon.getCouponDescription());
			couponsEntity.setDiscountCoupon(coupon.isDiscountCoupon());
			couponsEntity.setCouponDiscountPercentage(coupon.getCouponDiscountPercentage());
			couponsEntity.setActive(coupon.isActive());
			couponsEntity = couponRepository.save(couponsEntity);
			coupon.setCouponId(couponsEntity.getCouponId());
			return coupon;
		}).collect(Collectors.toList());
	}

	public int changeCouponStatus(int couponId, boolean status) {
		CouponsEntity couponsEntity = couponRepository.findById(couponId).get();
		couponsEntity.setActive(status);
		couponsEntity = couponRepository.save(couponsEntity);
		return couponId;
	}

}
