package com.asal.insurance_system;

import com.asal.insurance_system.Auth.AuthenticationResponse;
import com.asal.insurance_system.Configuration.JwtService;
import com.asal.insurance_system.DTO.UserDTO;
import com.asal.insurance_system.Model.User;
import com.asal.insurance_system.Repository.UserRepository;
import com.asal.insurance_system.Service.AuthenticationService;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static com.asal.insurance_system.Enum.Role.ADMIN;
import static com.asal.insurance_system.Enum.Role.USER;

@SpringBootApplication
@EnableScheduling
public class InsuranceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceSystemApplication.class, args);
	}

//	@Bean
//	CommandLineRunner initAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
//		return args -> {
//
//				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Define dateFormat here
//
//				User admin = new User();
//				admin.setFirstName("Admin");
//				admin.setLastName("admin");
//				admin.setEmail("admin@gmail.com");
//				admin.setPassword(passwordEncoder.encode("1234")); // Encrypted password
//				admin.setIdNumber("999999999");
//				admin.setRole(ADMIN);
//				admin.setDepartmentId(null);
//				admin.setHiringDate(dateFormat.parse("2023-06-15"));
//				admin.setSalary(7500.00f);
//				userRepository.save(admin);
//
//				Map<String, Object> extraClaims = new HashMap<>();
//				String token = jwtService.generateToken(extraClaims,admin);
//
//				System.out.println("Admin user created successfully.");
//				System.out.println("Admin JWT Token: "+ token);
//
//				User user = new User();
//				user.setFirstName("User");
//				user.setLastName("user");
//				user.setEmail("user@gmail.com");
//				user.setPassword(passwordEncoder.encode("1234")); // Encrypted password
//				user.setIdNumber("999999999");
//				user.setRole(USER);
//				user.setDepartmentId(null);
//				user.setHiringDate(dateFormat.parse("2023-06-15"));
//				user.setSalary(7500.00f);
//				userRepository.save(user);
//
//				Map<String, Object> extraClaims1 = new HashMap<>();
//				String token1 = jwtService.generateToken(extraClaims1,admin);
//
//				System.out.println("User user created successfully.");
//				System.out.println("User JWT Token: "+ token1);
//
//		};
//	}


}
// Admin: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3NDIwNjc5MjIsImV4cCI6MTc0MjA2OTM2Mn0.vWaOaCStNq4T3tjiIptVukeIbzmoHJcuZ8MwVCXXaXM
// user:  eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3NDIwNjc5MjIsImV4cCI6MTc0MjA2OTM2Mn0.vWaOaCStNq4T3tjiIptVukeIbzmoHJcuZ8MwVCXXaXM