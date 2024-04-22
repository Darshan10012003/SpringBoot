package com.serviceuser.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private String enrollmentNo;
	 
	 private String password;
	 
	 private String name;
	 
	 private String gender;
	
	 private Date birthDate;
	 
	 private String branch;
	 
	 private String contact;
	 
	 private String address;
	 
	 private String city;
	 
	 private Integer pincode;
	 
	 private String securityKey;
	 
	 private String role;
}
