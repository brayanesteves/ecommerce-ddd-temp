package coop.tecso.exam.todo1.hulkstore.util;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service

public class JwtProvider {
	
	@Value("${jwt.secret}")
	private String signingKey;
	
	
	public String generateJwt(UserDto user) {
		
		SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
		
		Map<String, String> claims = new HashMap<>();
		claims.put("userId", user.getId());
		claims.put("username", user.getUsername());
		claims.put("firstName", user.getFirstName());
		claims.put("lastName", user.getLastName());
		
		LocalDateTime now = LocalDateTime.now();
		
		Date issuetAt = toDate(now);
		Date expiration = toDate(now.plusHours(1));
		
		return Jwts.builder()
				   .setClaims(claims)
				   .setIssuedAt(issuetAt)
			       .setExpiration(expiration)
			       .signWith(key)
			       .compact();

	}
	
	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	
}
