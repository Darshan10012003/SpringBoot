package com.serviceauth.service;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.serviceauth.proxie.AuthProxie;

@Service
public interface AuthService {
	public Boolean ValidateToken();
	
	public String register(AuthProxie authProxie);
	
	public String adminregister(AuthProxie authProxie);
	
	public String Sendemail(String to,String subject,String body);
	
	public String Sendmailwithurl(String email, Date birthDate , String password);
	
	public String Otpsending(String email,Date birthDate);
	
//	public Boolean Otpverify(Long otp);
	
	public String ForgetPassword(String email,String password);
	
	public boolean Otpveri(String email,Long otp,Date birthDate);
	
	public String resetpassword(String resetUrl,String password);
//	public String ForgetPassword(String password);
	
	
}
