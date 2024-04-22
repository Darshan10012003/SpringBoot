package com.serviceauth.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.serviceauth.entites.AuthEntity;
import com.serviceauth.myuserdetails.MyUserDetails;
import com.serviceauth.proxie.AuthProxie;
import com.serviceauth.proxie.JwtRequest;
import com.serviceauth.proxie.JwtResponse;
import com.serviceauth.service.AuthService;
import com.serviceauth.utils.JwtUtils;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	MyUserDetails myUserDetails;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthService authService;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("auth/login")
	public ResponseEntity<JwtResponse> loginWithCredentials(@RequestBody JwtRequest jwtRequest) {

		if (jwtRequest != null) {
			try {
				Authentication authenticate = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
				authenticate.isAuthenticated();

			} catch (Exception e) {
				e.printStackTrace();
				throw new UsernameNotFoundException("user name not found!!");
			}
		}
		UserDetails userByUsername = myUserDetails.loadUserByUsername(jwtRequest.getUsername());
		String token = null;
		if (userByUsername != null) {
			token = jwtUtils.generateToken(userByUsername);
		}
		JwtResponse jwtResponse = new JwtResponse(token);
		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.ACCEPTED);

	}

	@PostMapping("auth/validate")
	public ResponseEntity<Boolean> ValidateToken() {
		System.out.println("validate");
		return new ResponseEntity<Boolean>(authService.ValidateToken(), HttpStatus.ACCEPTED);
	}

	@PostMapping("auth/register")
	public ResponseEntity<String> register(@RequestBody AuthProxie authProxie) {
		return new ResponseEntity<>(authService.register(authProxie), HttpStatus.ACCEPTED);
	}

	@PostMapping("auth/adminregister")
	public ResponseEntity<String> adminregister(@RequestBody AuthProxie authProxie) {
		return new ResponseEntity<>(authService.adminregister(authProxie), HttpStatus.ACCEPTED);
	}

	@GetMapping("/sendotp/{email}/{birthDate}")
	public String Otpsending(@PathVariable String email,@PathVariable Date birthDate) {
		return authService.Otpsending(email, birthDate);
	}
	
	@GetMapping("/sendMailWithUrl/{email}/{birthDate}/{password}")
	public String Sendmailwithurl(@PathVariable String email,@PathVariable Date birthDate,@PathVariable String password) {
		return authService.Sendmailwithurl(email, birthDate,password);
	}

	
//	@GetMapping("forgetpassword/{email}/{otp}/{password}")
//	public String ForgetPassword(@PathVariable String email,@PathVariable Long otp,@PathVariable("password") String newpassword) {
//		return authService.ForgetPassword(email, otp, newpassword);
//	}
	
	@GetMapping("forgetpassword/{email}/{password}")
	public String ForgetPassword(@PathVariable String email,@PathVariable("password") String newpassword) {
		return authService.ForgetPassword(email,newpassword);
	}
	
	@GetMapping("/verifyotp/{email}/{otp}/{birthDate}")
	public Boolean Otpveri(@PathVariable String email,@PathVariable Long otp,@PathVariable Date birthDate) {
		return authService.Otpveri(email, otp, birthDate);
	}
	@GetMapping("resetpassword/{resetUrl}/{password}")
	public String resetpassword(@PathVariable String resetUrl,@PathVariable String password) {
		return authService.resetpassword(resetUrl, password);
	}
	

}
