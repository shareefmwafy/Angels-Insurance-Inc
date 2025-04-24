package com.asal.insurance_system.Configuration;

import com.asal.insurance_system.Repository.CustomerRepository;
import com.asal.insurance_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;


    private static final String CUSTOMER_PREFIX = "customer_";
    private static final String USER_PREFIX = "systemUser_";
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            if (username.startsWith(CUSTOMER_PREFIX)) {
                String subUsername = username.substring(CUSTOMER_PREFIX.length());
                log.info("Log in with customer {}", subUsername);
                return (UserDetails) customerRepository.findByEmail(subUsername);
            }
            else if (username.startsWith(USER_PREFIX)) {
                String subUsername = username.substring(USER_PREFIX.length());
                log.info("Log in with system user {}", subUsername);
                return userRepository.findByEmail(subUsername)
                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
            }
            else {
                throw new UsernameNotFoundException("Invalid username prefix");
            }
        };
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
