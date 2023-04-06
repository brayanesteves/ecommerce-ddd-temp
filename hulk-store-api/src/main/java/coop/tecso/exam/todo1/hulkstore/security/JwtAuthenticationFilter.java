package coop.tecso.exam.todo1.hulkstore.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Value("${jwt.secret}")
	private String signingKey;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = request.getHeader("Authorization");
		
		if(jwt != null) {
			
			SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
			
			Claims claims = Jwts.parserBuilder()
					            .setSigningKey(key)
					            .build()
					            .parseClaimsJws(jwt)
					            .getBody();
			
			String username = String.valueOf(claims.get("username"));
			
			if(username != null) {
				
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				
				if(userDetails != null) {
					
					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities()
					);
					
					token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(token);
					
				}
				
			}
			
		}
		
		filterChain.doFilter(request, response);

	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().startsWith("/api/auth/");
	}

}
