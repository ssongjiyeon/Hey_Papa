package com.ssafy.heypapa.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

@Component
public class JwtTokenUtil {

	public final static long TOKEN_VALIDATION_SECOND = 1000L * 60 * 30; // 30분
	public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 60 * 24 * 2; //2일
	private static String secretKey;
	
	public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ISSUER = "ssafy.com";
	
    @Autowired
	public JwtTokenUtil(@Value("${spring.jwt.secret}") String secretKey) {
		this.secretKey = secretKey;
	}

	public static JWTVerifier getVerifier() {
        return JWT
                .require(Algorithm.HMAC512(secretKey.getBytes()))
                .withIssuer(ISSUER)
                .build();
    }
    
    public static String getToken(String userEmail) {
    		Date expires = JwtTokenUtil.getTokenExpiration();
        return JWT.create()
                .withSubject(userEmail)
                .withExpiresAt(expires)
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }

    public static String getToken(Instant expires, String userEmail) {
        return JWT.create()
                .withSubject(userEmail)
                .withExpiresAt(Date.from(expires))
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }
    
    public static String getRefreshToken(String userEmail) {
		
	    return JWT.create()
	            .withExpiresAt(new Date(REFRESH_TOKEN_VALIDATION_SECOND))
	            .withIssuer(ISSUER)
	            .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
	            .sign(Algorithm.HMAC512(secretKey.getBytes()));
	}
    
    public static Date getTokenExpiration() {
    		Date now = new Date();
    		return new Date(now.getTime() + TOKEN_VALIDATION_SECOND);
    }

    public static void handleError(String token) {
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC512(secretKey.getBytes()))
                .withIssuer(ISSUER)
                .build();

        try {
            verifier.verify(token.replace(TOKEN_PREFIX, ""));
        } catch (AlgorithmMismatchException ex) {
            throw ex;
        } catch (InvalidClaimException ex) {
            throw ex;
        } catch (SignatureGenerationException ex) {
            throw ex;
        } catch (SignatureVerificationException ex) {
            throw ex;
        } catch (TokenExpiredException ex) {
            throw ex;
        } catch (JWTCreationException ex) {
            throw ex;
        } catch (JWTDecodeException ex) {
            throw ex;
        } catch (JWTVerificationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void handleError(JWTVerifier verifier, String token) {
        try {
            verifier.verify(token.replace(TOKEN_PREFIX, ""));
        } catch (AlgorithmMismatchException ex) {
            throw ex;
        } catch (InvalidClaimException ex) {
            throw ex;
        } catch (SignatureGenerationException ex) {
            throw ex;
        } catch (SignatureVerificationException ex) {
            throw ex;
        } catch (TokenExpiredException ex) {
            throw ex;
        } catch (JWTCreationException ex) {
            throw ex;
        } catch (JWTDecodeException ex) {
            throw ex;
        } catch (JWTVerificationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
