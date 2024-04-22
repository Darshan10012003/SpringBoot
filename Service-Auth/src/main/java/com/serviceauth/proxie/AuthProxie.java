package com.serviceauth.proxie;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthProxie {
	private Long id;

	private String password;

	private String name;

	private String role;
	
	private String email;
	
	private Date birthDate;
	
	private String resetUrl;
	
	private LocalDateTime currentTimeandDate;
	
	private LocalDateTime otpVerifyTime;
	
	private long otp;
}
