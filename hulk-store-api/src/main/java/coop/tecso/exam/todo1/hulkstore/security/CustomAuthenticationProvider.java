package coop.tecso.exam.todo1.hulkstore.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;
	
	public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) {
		
		try {
			
			String username = authentication.getName();
			String password = String.valueOf(authentication.getCredentials());
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			boolean authenticated = ( passwordEncoder.matches(password, userDetails.getPassword()));
			
			if(!authenticated) {
				throw new BadCredentialsException("Bad credentials");
			}
			
			return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
			
		} catch(UsernameNotFoundException exp) {
			throw new BadCredentialsException("Bad credentials", exp);
		}
		
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return UsernamePasswordAuthenticationToken.class.equals(authenticationType);
	}

}
