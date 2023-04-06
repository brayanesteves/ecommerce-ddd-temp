package coop.tecso.exam.todo1.hulkstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import coop.tecso.exam.todo1.hulkstore.security.CustomAuthenticationProvider;
import coop.tecso.exam.todo1.hulkstore.security.CustomUserDetailsService;
import coop.tecso.exam.todo1.hulkstore.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { // Authentication configuration
		
		auth.authenticationProvider(authenticationProvider)
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();

		http.addFilterAt(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
		
		http.cors().and().authorizeRequests()
				.antMatchers("/api/auth/login", "/api/auth/sign-up").permitAll()
				.anyRequest().authenticated();
		
	}
	

}
