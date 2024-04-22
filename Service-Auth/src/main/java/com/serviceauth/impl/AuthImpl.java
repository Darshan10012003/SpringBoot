package com.serviceauth.impl;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

//import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.serviceauth.entites.AuthEntity;
import com.serviceauth.objectmapper.ObjectMappers;
import com.serviceauth.proxie.AuthProxie;
import com.serviceauth.repositories.AuthRepo;
import com.serviceauth.service.AuthService;

@Component
public class AuthImpl implements AuthService {

	@Autowired
	ObjectMappers objectMappers;

	@Autowired
	AuthRepo authRepo;

	@Autowired
	JavaMailSender javaMailSender;
	
//	@Autowired
//	RestTemplate restTemplate;

	@Override
	public Boolean ValidateToken() {
		// TODO Auto-generated method stub
		return Boolean.TRUE;
	}

	@Override
	public String register(AuthProxie authProxie) {
		// TODO Auto-generated method stub
		AuthEntity proxytoentity = objectMappers.proxytoentity(authProxie);
		proxytoentity.setRole("user");
		authRepo.save(proxytoentity);
//		String postForObject = restTemplate.postForObject("http://localhost:8092/User/user/register", authProxie, String.class);
		return "Successfully Register";
	}

	@Override
	public String adminregister(AuthProxie authProxie) {
		// TODO Auto-generated method stub
		AuthEntity proxytoentity = objectMappers.proxytoentity(authProxie);
		proxytoentity.setRole("admin");
		authRepo.save(proxytoentity);
		
		return "Successfully Register";
	}

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public String Sendemail(String to, String subject, String body) {
		// TODO Auto-generated method stub
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(body);
			javaMailSender.send(mimeMessage);
			return "Email Send";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@Override
	public String Otpsending(String email, Date birthDate) {
		// TODO Auto-generated method stub
		AuthEntity byEmail = authRepo.findByEmail(email);
//		AuthEntity byBirthDate = authRepo.findByBirthDate(birthDate);
		if (byEmail != null && birthDate.equals(byEmail.getBirthDate())) {
			Random random = new Random();
			long nextLong = random.nextLong(999999);
			AuthEntity authEntity = new AuthEntity();

//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime curreDateTime = LocalDateTime.now();

			authEntity.setId(byEmail.getId());
			authEntity.setName(byEmail.getName());
			authEntity.setPassword(byEmail.getPassword());
			authEntity.setRole(byEmail.getRole());
			authEntity.setEmail(byEmail.getEmail());
			authEntity.setBirthDate(byEmail.getBirthDate());
			authEntity.setCurrentTimeandDate(curreDateTime);
			authEntity.setOtp(nextLong);

			authRepo.save(authEntity);
			String body = "Your Otp is" + " " + nextLong;
			String subject = "Otp Verification Code..";
			Sendemail(email, subject, body);
			return "Send Successfully....";
		} else {
			return "Email Or BirthDate Not Match...";
		}
	}

	@Override
	public String Sendmailwithurl(String email, Date birthDate, String password) {
		// TODO Auto-generated method stub
		AuthEntity byEmail = authRepo.findByEmailAndBirthDateAndPassword(email, birthDate, password);
		System.out.println("date" + byEmail);
		if (byEmail != null && birthDate.equals(byEmail.getBirthDate()) && password.equals(byEmail.getPassword())) {
			Random random = new Random();
			long nextLong = random.nextLong(999999999);
			String uuidString = UUID.randomUUID().toString();
			System.out.println(uuidString);
			String urlforReset = uuidString + nextLong;	
			AuthEntity authEntity = new AuthEntity();

			authEntity.setId(byEmail.getId());
			authEntity.setName(byEmail.getName());
			authEntity.setPassword(byEmail.getPassword());
			authEntity.setRole(byEmail.getRole());
			authEntity.setEmail(byEmail.getEmail());
			authEntity.setBirthDate(byEmail.getBirthDate());
			authEntity.setOtp(byEmail.getOtp());
			authEntity.setResetUrl(urlforReset);

			authRepo.save(authEntity);
			
			String body = "Click in The Link And Reset Your Password." + "   " 
					+ "http://localhost:4200/resetpasswordafter" + "?token="+urlforReset;
			String subject = "Reset Password Link";
			Sendemail(email, subject, body);
			return "Send Link In Email Successfully....";
		} else {
			return "Email Or BirthDate Or Password Not Match...";
		}
	}

	@Override
	public String ForgetPassword(String email, String password) {
		// TODO Auto-generated method stub
		AuthEntity byEmail = authRepo.findByEmail(email);
		if (byEmail != null) {
			AuthEntity authEntity = new AuthEntity();
			authEntity.setId(byEmail.getId());
			authEntity.setName(byEmail.getName());
			authEntity.setPassword(password);
			authEntity.setRole(byEmail.getRole());
			authEntity.setEmail(byEmail.getEmail());
			authEntity.setBirthDate(byEmail.getBirthDate());
			authEntity.setOtp(byEmail.getOtp());
			authRepo.save(authEntity);
			return "Update Successfully..";
		} else {
			return "Password not update";
		}
	}

	@Value("${otpvalidationtimeinminutes}")
	private Integer otptime;
	@Override
	public boolean Otpveri(String email, Long otp, Date birthDate) {
		// TODO Auto-generated method stub
		AuthEntity byEmail = authRepo.findByEmail(email);
		LocalDateTime otpverifytime = LocalDateTime.now();
		AuthEntity authEntity = new AuthEntity();

		authEntity.setId(byEmail.getId());
		authEntity.setName(byEmail.getName());
		authEntity.setPassword(byEmail.getPassword());
		authEntity.setRole(byEmail.getRole());
		authEntity.setEmail(byEmail.getEmail());
		authEntity.setBirthDate(byEmail.getBirthDate());
		authEntity.setCurrentTimeandDate(byEmail.getCurrentTimeandDate());
		authEntity.setOtp(byEmail.getOtp());
		authEntity.setOtpVerifyTime(otpverifytime);
		authRepo.save(authEntity);
System.out.println(otptime);
		int result = 0;
		if (byEmail != null && email.equals(byEmail.getEmail()) && otp.equals(byEmail.getOtp())
				&& birthDate.equals(byEmail.getBirthDate())) {
			LocalDateTime dbcurrentTimeandDate = byEmail.getCurrentTimeandDate();
			result = otpverifytime.getMinute() - dbcurrentTimeandDate.getMinute();
			if (result <= otptime) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	@Override
	public String resetpassword(String resetUrl, String password) {
		// TODO Auto-generated method stub
		AuthEntity byResetUrl = authRepo.findByResetUrl(resetUrl);
		if(byResetUrl!= null && resetUrl.equals(byResetUrl.getResetUrl())) {
		AuthEntity authEntity = new AuthEntity();
		System.out.println(resetUrl);

		authEntity.setId(byResetUrl.getId());
		authEntity.setName(byResetUrl.getName());
		authEntity.setPassword(password);
		authEntity.setRole(byResetUrl.getRole());
		authEntity.setEmail(byResetUrl.getEmail());
		authEntity.setBirthDate(byResetUrl.getBirthDate());
		authEntity.setResetUrl(resetUrl);
//		authEntity.setCurrentTimeandDate(byResetUrl.getCurrentTimeandDate());
//		authEntity.setOtp(byResetUrl.getOtp());
		authRepo.save(authEntity);
		return "Reset Password Successfully..";
		}
		return "Your Password Was Not Reset..";
	}

}
