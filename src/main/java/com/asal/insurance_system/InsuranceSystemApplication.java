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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static com.asal.insurance_system.Enum.Role.ADMIN;
import static com.asal.insurance_system.Enum.Role.USER;

@SpringBootApplication
public class InsuranceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner initAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		return args -> {
			if (userRepository.findByEmail("user@gmail.com").isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Define dateFormat here

				User admin = new User();
				admin.setFirstName("User");
				admin.setLastName("User");
				admin.setEmail("user@gmail.com");
				admin.setPassword(passwordEncoder.encode("user@123")); // Encrypted password
				admin.setIdNumber("999999999");
				admin.setRole(USER);
				admin.setDepartmentId(null);
				admin.setHiringDate(dateFormat.parse("2023-06-15"));
				admin.setSalary(7500.00f);
				userRepository.save(admin);

				Map<String, Object> extraClaims = new HashMap<>();
				String token = jwtService.generateToken(extraClaims,admin);

				System.out.println("user user created successfully.");
				System.out.println("User JWT Token: "+ token);
			}
			else{
				System.out.println("User Already Exist");
			}
		};
	}


}
//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3NDIwNjIyMDMsImV4cCI6MTc0MjA2MzY0M30.1gyA1GezZRFf9eu9n4ZkDSdzWG4Q5YbTKiePrnJMtq8