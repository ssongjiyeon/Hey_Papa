package com.ssafy.heypapa.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssafy.heypapa.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {
	
	public final static long TOKEN_VALIDATION_SECOND = 1000L * 10;
	public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 24 * 2;
	
	final static public String ACCESS_TOKEN_NAME = "accessToken";
	final static public String REFRESH_TOKEN_NAME = "refreshToken";
	
	@Value("${spring.jwt.secret")
	private String SECRET_KEY;
	
	private Key getSigningKey(String secretKey) {
		byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public Claims extractAllClaims(String token) throws ExpiredJwtException {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey(SECRET_KEY))
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String getUserNickname(String token) {
		return extractAllClaims(token).get("nickname", String.class);
	}
	
	public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }
	
	public String doGenerateToken(String nickname, long expireTime) {
		Claims claims = Jwts.claims();
		claims.put("nickname", nickname);
		
		String jwt = Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expireTime))
				.signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
				.compact();
		
		return jwt;
	}
	
	public String generateToken(User user) {
		return doGenerateToken(user.getNickname(), TOKEN_VALIDATION_SECOND);
	}
	
	public String generateRefreshToken(User user) {
		return doGenerateToken(user.getNickname(), REFRESH_TOKEN_VALIDATION_SECOND);
	}
	
	public Boolean validateToken(String token, User user) {
		final String nickname = getUserNickname(token);
		
		return (nickname.equals(user.getNickname()) && !isTokenExpired(token));
	}
}
