package com.serviceauth.myuserdetails;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.serviceauth.entites.AuthEntity;
import com.serviceauth.repositories.AuthRepo;

@Component
@Configuration
public class MyUserDetails implements UserDetailsService {

	@Autowired
	private AuthRepo userRepo;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
//		if(username.equals("dk")) {
//			return new User(username,passwordEncoder().encode("10012003"),new ArrayList<>());
//		}
//		throw new UsernameNotFoundException("not found!!");
//	}

		AuthEntity byName = userRepo.findByName(username);
		System.out.println("name is "+byName);
		if (byName.getRole().equals("user")) {
			if (username.equals(byName.getName())) {
				return new User(username, passwordEncoder().encode(byName.getPassword()), new ArrayList<>());
			}
		} else {
			//AuthEntity name = userRepo.findByName(username);
			if (byName.getRole().equals("admin")) {
				if (username.equals(byName.getName())) {
					return new User(username, passwordEncoder().encode(byName.getPassword()), new ArrayList<>());
				}
			}
		}

		throw new UsernameNotFoundException("Not Found!!");
	}
//		
//		if (role.equals(Role.ROlE_USER.toString())) {
//			AuthEntity byName = userRepo.findByName(username);
//			if (username.equals(byName.getName())) {
//				return new User(username, passwordEncoder().encode(byName.getPassword()), new ArrayList<>());
//			}
//		} else {
//			AuthEntity byName = userRepo.findByName(username);
//			if (role.equals("admin")) {
//				if (username.equals(byName.getName())) {
//					return new User(username, passwordEncoder().encode(byName.getPassword()), new ArrayList<>());
//				}
//			}
//		}
//
//		throw new UsernameNotFoundException("Not Found!!");
//	}	
}
