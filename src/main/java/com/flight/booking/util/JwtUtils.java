package com.flight.booking.util;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

public class JwtUtils {

	public static Claims parseToken(String token, String jwtSecret) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

	public static String buildToken(Map<String, Object> claimMap, String jwtSubject, String jwtSecret, Date iat,
			Date exp) {
		return Jwts.builder().setSubject(jwtSubject).addClaims(claimMap).setIssuedAt(iat).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(jwtSecret)).compact();
	}

}