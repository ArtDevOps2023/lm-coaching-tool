package com.lmph.be.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final String LOGIN_URL = "/login";
	private final String HOME_URL = "/employees";
	private final String LOGOUT_URL = "/logout";
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//http.formLogin();
		
		http.formLogin(login -> login
								.loginProcessingUrl(LOGIN_URL).loginPage(LOGIN_URL).defaultSuccessUrl(HOME_URL, true)
								.failureUrl(LOGIN_URL + "?error=true").permitAll())
			.logout(logout -> logout.logoutSuccessUrl(LOGIN_URL).logoutUrl(LOGOUT_URL));
		
		http.authorizeHttpRequests(
				authz -> authz.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers("/").permitAll().anyRequest()
				.authenticated())
		.cors((cors) -> cors.disable()).csrf((csrf) -> csrf.disable());
		
		return http.build();
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
	  
	  UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("lmph"))
				.roles("USER")
				.build();
	  UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("lmph"))
				.roles("USER", "ADMIN")
				.build();
			
	  //UserDetails user = User.withUsername("javainuse").password(passwordEncoder().encode("javainuse"))
	  //  .authorities("read").build();

	  return new InMemoryUserDetailsManager(user, admin);
	 }

}
