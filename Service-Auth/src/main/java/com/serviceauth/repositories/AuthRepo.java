package com.serviceauth.repositories;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serviceauth.entites.AuthEntity;

@Repository
public interface AuthRepo extends CrudRepository<AuthEntity, Long> {
	AuthEntity findByName(String name);

	AuthEntity findByEmail(String email);

	AuthEntity findByOtp(Long otp);

	AuthEntity findByBirthDate(Date birthDate);

	AuthEntity findByEmailAndBirthDateAndPassword(String email, Date birthDate, String password);

	AuthEntity findByOtpAndCurrentTimeandDate(Long otp, Timestamp currentTimeandDate);
	
	AuthEntity findByResetUrl(String resetUrl);
}
