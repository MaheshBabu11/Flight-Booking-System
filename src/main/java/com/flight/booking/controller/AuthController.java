package com.flight.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.booking.model.User;
import com.flight.booking.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("user")
@CrossOrigin
@Api(tags = { "User" }, value = "user")
public class AuthController {

	@Autowired
	private AuthService authService;

	@GetMapping("user-details")
	@ApiOperation(value = "user-details", nickname = "user-details", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = User.class) })
	public User userDetails(@RequestParam(name = "signInId", required = true) String signInId) {
		return authService.userDetails(signInId);
	}

	@PostMapping("signin")
	@ApiOperation(value = "signin", nickname = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = String.class) })
	public String signIn(@RequestHeader(name = "password", required = true) String password,
			@RequestParam(name = "signInId", required = true) String signInId,
			@RequestParam(name = "adminLogin", defaultValue = "false") String adminLogin) {
		return authService.signIn(signInId, password, Boolean.parseBoolean(adminLogin));
	}

	@PostMapping("register")
	@ApiOperation(value = "register", nickname = "register", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = String.class) })
	public String signUp(@RequestHeader(name = "password", required = true) String password, @RequestBody User userRq) {
		return authService.signUp(userRq, password);
	}

	@PostMapping("signout")
	@ApiOperation(value = "signout", nickname = "signout", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = Void.class) })
	public void signOut(@RequestHeader String accessToken) {
		authService.signOut(accessToken);
	}

	@PutMapping("update-details")
	@ApiOperation(value = "update-details", nickname = "update-details", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = User.class) })
	public User updateUserDetails(@RequestBody User userRq) {
		return authService.updateUserDetails(userRq);
	}

	@PutMapping("update-pasword")
	@ApiOperation(value = "update-pasword", nickname = "update-pasword", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok", response = Void.class) })
	public void updatePassword(@RequestHeader(name = "password", required = true) String password,
			@RequestParam(name = "userId", required = true) Integer userId) {
		authService.updatePassword(userId, password);
	}

}
