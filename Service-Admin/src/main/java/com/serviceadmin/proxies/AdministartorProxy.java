package com.serviceadmin.proxies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministartorProxy {
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
