package org.sid.config;

import org.sid.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AuthenticationSuccessHandler successHandler;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 @Autowired
	   public PasswordEncoder BCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	
	
	// @Override
	// public void configure(AuthenticationManagerBuilder auth) throws Exception {
	// 	auth.inMemoryAuthentication()
	// 		.withUser("user").password("{noop}password").roles("USER")
	// 		.and()
	// 		.withUser("admin").password("{noop}password").roles("ADMIN");
	// }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").anonymous()//hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
			.antMatchers("/new").permitAll()//hasAnyAuthority("ADMIN", "CREATOR")
			.antMatchers("/edit/**").permitAll()//hasAnyAuthority("ADMIN", "EDITOR")
			.antMatchers("/delete/**").permitAll()//hasAuthority("ADMIN")
			.antMatchers("/user").hasAnyRole("USER")
			.antMatchers("/admin").hasAnyRole("ADMIN")
			.and().formLogin().loginPage("/login")
			         .successHandler(successHandler).permitAll()
			.and().logout().permitAll()
			
		;
            
            
            
			// .anyRequest().anonymous()//authenticated()
			// .exceptionHandling().accessDeniedPage("/403");
	}
	

	
	
	
	
}
