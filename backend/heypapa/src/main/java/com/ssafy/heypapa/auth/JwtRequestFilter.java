package com.ssafy.heypapa.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ssafy.heypapa.entity.User;
import com.ssafy.heypapa.util.CookieUtil;
import com.ssafy.heypapa.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private PapaUserDetailService userService;
	
	@Autowired
	private JwtTokenUtil jwtUtil;
	
	@Autowired
	private CookieUtil cookieUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final Cookie jwtToken = cookieUtil.getCookie(request, JwtTokenUtil.ACCESS_TOKEN_NAME);
		
		String nickname = null;
		String jwt = null;
		String refreshJwt = null;
		String refreshNickname = null;
		
		try {
			
			if(jwtToken != null) {
				jwt = jwtToken.getValue();
				nickname = jwtUtil.getUserNickname(jwt);
			}
			
			if(nickname != null) {
				UserDetails userDetails = userService.loadUserByUsername(nickname);
				
				if(jwtUtil.validateToken(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			
		} catch(ExpiredJwtException e) {
			Cookie refreshToken = cookieUtil.getCookie(request, JwtTokenUtil.REFRESH_TOKEN_NAME);
			
			if(refreshToken != null) {
				refreshJwt = refreshToken.getValue();
			}
		} catch(Exception e) {
			
		}
		
		try {
			if(refreshJwt != null) {
				refreshNickname = redisUtil.getData(refreshJwt);
				
				if(refreshNickname.equals(jwtUtil.getUserNickname(refreshJwt))) {
					UserDetails userDetails = userService.loadUserByUsername(refreshNickname);
					UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					upaToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(upaToken);
					
					User user = new User();
					user.setNickname(refreshNickname);
					String newToken = jwtUtil.generateToken(user);
					
					Cookie newAccessToken = cookieUtil.createCookie(JwtTokenUtil.ACCESS_TOKEN_NAME, newToken);
					response.addCookie(newAccessToken);
				}
			}
		} catch(ExpiredJwtException e){

        }
		
		filterChain.doFilter(request, response);
		
	}
	
	
}
