package com.serviceauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.serviceauth.entrypoint.AuthEntrypoint;
import com.serviceauth.jwtfilter.JwtFilter;
import com.serviceauth.myuserdetails.MyUserDetails;

@Configuration
@EnableWebSecurity
public class JwtConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MyUserDetails myUserDetails;
	@Autowired
	private AuthEntrypoint authEntrypoint;
	@Autowired
	private JwtFilter jwtFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(myUserDetails);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
						requests -> requests.antMatchers("/auth/login","/savedata","/auth/register","/auth/adminregister","/sendotp/{email}/{birthDate}","/verifyotp/**","/sendMailWithUrl/**","/resetpassword/**","/forgetpassword/**").permitAll().anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(authEntrypoint))
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
