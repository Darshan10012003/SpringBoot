package com.serviceauth.proxie;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private String enrollmentNo;

	private String password;

	private String name;

	private String gender;

	private Date dob;

	private String branch;

	private String contact;

	private String address;

	private String city;

	private Integer pincode;

	private String securityKey;

	private String role;
}
