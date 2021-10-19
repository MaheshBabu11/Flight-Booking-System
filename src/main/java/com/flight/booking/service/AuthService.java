package com.flight.booking.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flight.booking.model.User;
import com.flight.booking.repository.BlockedTokenRepository;
import com.flight.booking.repository.RoleRepository;
import com.flight.booking.repository.UserCredenttialRepository;
import com.flight.booking.repository.UserRepository;
import com.flight.booking.repository.entity.BlockedTokenEntity;
import com.flight.booking.repository.entity.UserCredentialEntity;
import com.flight.booking.repository.entity.UserEntity;
import com.flight.booking.util.JwtUtils;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserCredenttialRepository userCredenttialRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BlockedTokenRepository blockedTokenRepository;

	@Value("${application.jwt.subject}")
	private String jwtSubject;

	@Value("${application.jwt.secret}")
	private String jwtSecret;

	@Value("${application.jwt.expTime}")
	private int expTime;

	public String signIn(String signInId, String password, boolean adminLogin) {
		return Optional.ofNullable(userRepository.findByEmail(signInId).orElse(null))
				.filter(userEntity -> userEntity != null && userEntity.getUserCredentials() != null
						&& password.equals(userEntity.getUserCredentials().getPassword()) && adminLogin
								? userEntity.getRole().getRoleName().equalsIgnoreCase("ADMIN")
								: true)
				.map(userEntity -> {
					Map<String, Object> claimMap = new HashMap<>();
					claimMap.put("userId", userEntity.getUserId());
					claimMap.put("email", userEntity.getEmail());
					claimMap.put("name", userEntity.getUserName());
					claimMap.put("phone", userEntity.getPhone());
					claimMap.put("role", userEntity.getRole().getRoleName());
					LocalDateTime currentTime = LocalDateTime.now();
					Date iat = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
					Date exp = Date.from(currentTime.plusHours(expTime).atZone(ZoneId.systemDefault()).toInstant());
					return JwtUtils.buildToken(claimMap, jwtSubject, jwtSecret, iat, exp);
				}).orElse(null);
	}

	public String signUp(User userRq, String password) {
		if (Optional.ofNullable(userRepository.findByEmail(userRq.getEmail()).orElse(null)).isPresent()) {
			throw new RuntimeException("Email already added");
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(userRq.getEmail());
		userEntity.setUserName(userRq.getUserName());
		userEntity.setPhone(userRq.getPhone());
		userEntity.setRole(roleRepository.findByRoleName(userRq.getRole()).get());
		UserCredentialEntity credentialsEntity = new UserCredentialEntity();
		credentialsEntity.setPassword(password);
		credentialsEntity = userCredenttialRepository.save(credentialsEntity);
		userEntity.setUserCredentials(credentialsEntity);
		userEntity = userRepository.save(userEntity);
		Map<String, Object> claimMap = new HashMap<>();
		claimMap.put("userId", userEntity.getUserId());
		claimMap.put("email", userEntity.getEmail());
		claimMap.put("name", userEntity.getUserName());
		claimMap.put("phone", userEntity.getPhone());
		claimMap.put("role", userEntity.getRole().getRoleName());
		LocalDateTime currentTime = LocalDateTime.now();
		Date iat = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
		Date exp = Date.from(currentTime.plusHours(expTime).atZone(ZoneId.systemDefault()).toInstant());
		return JwtUtils.buildToken(claimMap, jwtSubject, jwtSecret, iat, exp);
	}

	public void signOut(String accessToken) {
		BlockedTokenEntity tokenEntity = new BlockedTokenEntity();
		tokenEntity.setToken(accessToken);
		blockedTokenRepository.save(tokenEntity);
	}

	public User updateUserDetails(User userRq) {
		return Optional.ofNullable(userRepository.findById(userRq.getUserId()).orElse(null)).map(userEntity -> {
			userEntity.setEmail(userRq.getEmail());
			userEntity.setUserName(userRq.getUserName());
			userEntity.setPhone(userRq.getPhone());
			userEntity.setEmailVerified(userRq.isEmailVerified());
			userEntity.setRole(roleRepository.findByRoleName(userRq.getRole()).get());
			userRepository.save(userEntity);
			return userRq;
		}).orElse(null);
	}

	public void updatePassword(int userId, String password) {
		UserEntity userEntity = userRepository.findById(userId).get();
		UserCredentialEntity credentialsEntity = userEntity.getUserCredentials();
		credentialsEntity.setPassword(password);
		credentialsEntity = userCredenttialRepository.save(credentialsEntity);
	}

	public User userDetails(String signInId) {
		return Optional.ofNullable(userRepository.findByEmail(signInId).orElse(null)).map(userEntity -> {
			User user = new User();
			user.setUserId(userEntity.getUserId());
			user.setEmail(userEntity.getEmail());
			user.setUserName(userEntity.getUserName());
			user.setPhone(userEntity.getPhone());
			user.setEmailVerified(userEntity.isEmailVerified());
			user.setRole(userEntity.getRole().getRoleName());
			userRepository.save(userEntity);
			return user;
		}).orElse(null);
	}

}
