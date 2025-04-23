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

}