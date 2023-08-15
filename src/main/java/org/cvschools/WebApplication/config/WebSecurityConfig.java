package org.cvschools.WebApplication.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * security configuration that defines which areas of the system are accessible without logging in
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

	//bean for creating a BCryptPasswordEncoder
	@Bean
	public static PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	//filter chain configuration settings
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/index").authenticated()				
				.requestMatchers("/register**", "/users**").hasRole("ADMIN")				
				.requestMatchers("/403b**").hasAnyRole("ADMIN", "BUSINESS")
			)					
			.formLogin((form) -> form
				.loginPage("/login")
				.loginProcessingUrl("/login")				
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());			

		return http.build();
	}	

}

