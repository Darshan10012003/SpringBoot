package com.serviceadmin.entities;

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
public class Administartor {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String password;
	private String name;
	private String gender;
	private String contact;
	private String address;
	private String city;
	private String pincode;
	private String securityKey;
	private String role;
}
