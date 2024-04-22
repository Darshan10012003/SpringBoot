package com.serviceadmin.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.serviceadmin.entities.Administartor;
import com.serviceadmin.objectmapeer.ObjectMappers;
import com.serviceadmin.proxies.AdministartorProxy;
import com.serviceadmin.proxies.StudentProxy;
import com.serviceadmin.repositories.AdministartorRepo;
import com.serviceadmin.services.AdministartorServices;
import com.serviceadmin.tokenvalidate.TokenCheck;

@Component
public class AdministrativeImpl implements AdministartorServices {
	@Autowired
	private AdministartorRepo administartorRepo;

	@Autowired
	private ObjectMappers objectMapper;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private TokenCheck tokenCheck;

	@Override
	public String registerWithDetails(AdministartorProxy administartorProxy, Map<String, String> headerdata) {
		// TODO Auto-generated method stub
		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if (tokencheckisvalidate == true) {
			Administartor proxytoentity = objectMapper.proxytoentity(administartorProxy);
			administartorRepo.save(proxytoentity);
			return "Save Successfully";
		}
		return null;
	}

//	@Override
//	public String updateUserDetails(String enrollmentNo, StudentProxy studentProxy) {
//		// TODO Auto-generated method stub
//		Optional<Student> byId = studentRepo.findById(enrollmentNo);
//		if (byId.isPresent()) {
//			Student student = Student.studentstatic(enrollmentNo, studentProxy.getPassword(), studentProxy.getName(),
//					studentProxy.getGender(), studentProxy.getDob(), studentProxy.getBranch(), studentProxy.getContact(),
//					studentProxy.getAddress(), studentProxy.getCity(), studentProxy.getPincode(),
//					studentProxy.getSecurityKey(), studentProxy.getRole());
//			// Student student = byId.get();
////			if(studentProxy.getPassword()!=null) {
////				student.setPassword(studentProxy.getPassword());
////			}
//			studentRepo.save(student);
//			return "ID Update Successfully!";
//		}
//		return "Not ID Found";
//	}
//
	@Override
	public String updateAdminDetails(Long id, AdministartorProxy administartorProxy, Map<String, String> headerdata) {
		// TODO Auto-generated method stub
		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if (tokencheckisvalidate == true) {
			java.util.Optional<Administartor> byId = administartorRepo.findById(id);
			if (byId.isPresent()) {
				Administartor proxytoentity = objectMapper.proxytoentity(administartorProxy);
				administartorRepo.save(proxytoentity);
				return "ID Update Successfully!";
			}
		}
		return "Not ID Found";
	}
//
//	@Override
//	public String deleteUser(String enrollmentNo) {
//		// TODO Auto-generated method stub
//		Optional<Student> byId = studentRepo.findById(enrollmentNo);
//		if (byId.isPresent()) {
//			studentRepo.deleteById(enrollmentNo);
//			return "deleted Successfully";
//		}
//		return "Not Id Found";
//
//	}
//
//	@Override
//	public List<StudentProxy> getUsers() {
//		// TODO Auto-generated method stub
//
//		List<StudentProxy> studentProxies = new ArrayList<>();
//		List<Student> students = (List<Student>) studentRepo.findAll();
//		for (Student student : students) {
//			StudentProxy entitytoproxy = objectMapper.entitytoproxy(student);
//			studentProxies.add(entitytoproxy);
//		}
//		return studentProxies;
//	}
//
//	@Override
//	public StudentProxy getUser(String enrollmentNo) {
//		// TODO Auto-generated method stub
//		System.out.println("++++++++++++++++");
//		Student student = studentRepo.findById(enrollmentNo).get();
//		System.out.println("daadadad "+student);
//		StudentProxy studentproxy = objectMapper.entitytoproxy(student);
//		return studentproxy;
//	}

	@Override
	public AdministartorProxy getAdmin(Long id, Map<String, String> headerdata) {
		// TODO Auto-generated method stub
		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if (tokencheckisvalidate == true) {
			Administartor administartor = administartorRepo.findById(id).get();
			AdministartorProxy administartorProxy = objectMapper.entitytoProxy(administartor);
			return administartorProxy;
		}
		return null;
	}

	@Override
	public String updateUserDetails(String enrollmentNo, StudentProxy studentProxy, Map<String, String> headerdata) {
		// TODO Auto-generated method stub
		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if (tokencheckisvalidate == true) {
			String postForObject = restTemplate.postForObject("http://USER-SERVICE/User/user/updateUser/" + enrollmentNo,
					studentProxy, String.class);
			return postForObject;
		}
		return null;
	}

	@Override
	public List<StudentProxy> getUsers(Map<String, String> headerdata) {
		// TODO Auto-generated method stub
		// @SuppressWarnings("unchecked")
//	HttpHeaders httpHeaders = new HttpHeaders();
//	httpHeaders.set("Authorization",headerdata.get("token"));
//	httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//	HttpEntity<Object> httpEntity = new HttpEntity<>(null,httpHeaders);
//	Boolean postForObject = restTemplate.postForObject("http://localhost:8091/auth/validate",httpEntity,Boolean.class);

		// TokenCheck tokenCheck= new TokenCheck();
		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if (tokencheckisvalidate == true) {
			ArrayList<StudentProxy> forObject = restTemplate.getForObject("http://USER-SERVICE/User/user/getAllUser",
					ArrayList.class);
			return forObject;
		}
		return null;
	}

	@Override
	public StudentProxy getUser(String enrollmentNo, Map<String, String> headerdata) {
		// TODO Auto-generated method stub
		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if (tokencheckisvalidate == true) {
			System.out.println("true");
			ResponseEntity<StudentProxy> forEntity = restTemplate.getForEntity("http://USER-SERVICE/User/user/getUser/"+enrollmentNo,
					StudentProxy.class);
			StudentProxy body = forEntity.getBody();
			HttpHeaders headers = forEntity.getHeaders();
			System.out.println("Header"+ headers);
			HttpStatus statusCode = forEntity.getStatusCode();
			System.out.println("statuscode" + statusCode);
			return body;
		} else {
			return null;
		}
	}

	@Override
	public String deleteUser(String enrollmentNo, Map<String, String> headerdata) {
		// TODO Auto-generated method stub
		return null;
	}

}
