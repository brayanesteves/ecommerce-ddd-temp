package coop.tecso.exam.todo1.hulkstore.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import coop.tecso.exam.todo1.hulkstore.domain.model.User;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	public static final String USER_ROLE = "USER";
	
	private String username;
	private String password;
	
	public CustomUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList( new SimpleGrantedAuthority(USER_ROLE) );
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
