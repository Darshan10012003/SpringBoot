package com.serviceuser.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.serviceuser.entities.Student;
import com.serviceuser.proxies.StudentProxy;
import com.serviceuser.repositories.EducationDetailsRepo;
import com.serviceuser.repositories.StudentRepo;
import com.serviceuser.service.StudentServices;
import com.serviceuser.tokencheck.TokenCheck;
import com.serviceuser.utils.ObjectMapper;

@Component
public class StudentImpl implements StudentServices {
	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	StudentRepo studentRepo;

	@Autowired
	TokenCheck tokenCheck;

	@Autowired
	Faker faker;

//	@Autowired
//	FeeDetailsRepo feeDetailsRepo;

	@Autowired
	EducationDetailsRepo educationDetailsRepo;

	@Override
	public String registerWithDetails(StudentProxy studentProxy, Map<String, String> headerdata) {
		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if (tokencheckisvalidate == true) {
			Student proxytoentity = objectMapper.proxytoentity(studentProxy);
			studentRepo.save(proxytoentity);
//			(StudentImpl.class);

			return "Save Successfully";
		}
		return null;
	}

	@Override
	public String updateUserDetails(String enrollmentNo, StudentProxy studentProxy) {
		// TODO Auto-generated method stub
		Optional<Student> byId = studentRepo.findById(enrollmentNo);
		if (byId.isPresent()) {
			Student proxytoentity = objectMapper.proxytoentity(studentProxy);
			studentRepo.save(proxytoentity);
		}
		return "Update Successfully";
	}

	@Override
	public StudentProxy getUser(String enrollmentNo) {
		// TODO Auto-generated method stub
		Student student = studentRepo.findById(enrollmentNo).get();
		StudentProxy studentProxy = objectMapper.entitytoproxy(student);
		return studentProxy;
	}

	@Override
	public List<StudentProxy> getAllUser() {
		// TODO Auto-generated method stub
		List<StudentProxy> list = new ArrayList<>();
		List<Student> all = (List<Student>) studentRepo.findAll();
		for (Student student : all) {
			StudentProxy entitytoproxy = objectMapper.entitytoproxy(student);
			list.add(entitytoproxy);
		}
		return list;
	}

	@Override
	public String updateUserDetailsU(String enrollmentNo, StudentProxy studentProxy, Map<String, String> headerdata) {
		// TODO Auto-generated method stub
		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if (tokencheckisvalidate == true) {
			Optional<Student> byId = studentRepo.findById(enrollmentNo);
			if (byId.isPresent()) {
				Student proxytoentity = objectMapper.proxytoentity(studentProxy);
				studentRepo.save(proxytoentity);
			}
			return "Update Successfully";
		}
		return "Not Authorize Person";
	}

	@Override
	public StudentProxy getUserU(String enrollmentNo, Map<String, String> headerdata) {
		// TODO Auto-generated method stub

		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if (tokencheckisvalidate == true) {
			Student student = studentRepo.findById(enrollmentNo).get();
			StudentProxy studentProxy = objectMapper.entitytoproxy(student);
			return studentProxy;
		}
		return null;
	}

	@Override
	public List<StudentProxy> getAllUserU(Integer pagenumber, Integer pagesize, Map<String, String> headerdata) {
		// TODO Auto-generated method stub

		Boolean tokencheckisvalidate = tokenCheck.tokencheckisvalidate(headerdata);
		if (tokencheckisvalidate == true) {
			List<StudentProxy> list = new ArrayList<>();
			Pageable pageable = PageRequest.of(pagenumber, pagesize);
			Page<Student> all = studentRepo.findAll(pageable);
			List<Student> content = all.getContent();
//			List<EntityExcelSheet> content = all.getContent();

			studentRepo.findAll(pageable);
			for (Student student : content) {
				StudentProxy entitytoproxy = objectMapper.entitytoproxy(student);
				list.add(entitytoproxy);
			}
			return list;
		}
		return null;
	}

	@Override
	public String savedata() {
		// TODO Auto-generated method stub
		for (int i = 2; i <= 50; i++) {
			Student student = new Student("78", faker.number().digit(), faker.name().firstName(), "male",
					faker.date().birthday(), faker.university().name(), faker.number().digit(),
					faker.address().fullAddress(), faker.address().cityName(), faker.number().randomDigit(),
					faker.number().digit(), "user");
			studentRepo.save(student);
		}
		return "save";
	}

}
