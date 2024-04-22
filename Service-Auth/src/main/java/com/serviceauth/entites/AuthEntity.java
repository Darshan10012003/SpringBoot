package com.serviceauth.entites;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor(staticName = "studentstatic")
@NoArgsConstructor
public class AuthEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String password;

	private String name;
	
	private String email;
	
	private Date birthDate;
	
	private Long otp;
	
	private String resetUrl;
	
	private LocalDateTime currentTimeandDate;
	
	private LocalDateTime otpVerifyTime;

	private String role;
}
