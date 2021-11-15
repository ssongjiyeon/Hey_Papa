package com.ssafy.heypapa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// 스프링 시큐리티에서는 @CrossOrigin만으로 다른 리소스간 요청을 수락할 수 없게 설정되어있다.
@Configuration
public class MyCorsFilter extends CorsFilter {
	
	// 필터가 생성될 때 필터를 설정
	public MyCorsFilter() {
		super(configurationSource());
	}

	// @Crossorigin으로 처리할 수 없는 security로 인한 cors를 패스시킴
	private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 자격증명 허용 
        config.addAllowedOriginPattern("*"); // 자격증명을 허용할 때 출처를 허용하는 메소드
        config.addAllowedHeader("*"); // 허용할 헤더 설정
        config.addAllowedMethod("*"); // 허용할 요청 (get post put delete 등등)
        config.addExposedHeader("Authorization"); // 노출할 헤더 설정 원래 authorization은 노출이 안되서 명시적으로 선언해야 보여짐
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // corsconfiguration을 url경로 패턴으로 사용하기위한 구현체 
        source.registerCorsConfiguration("/**", config); // 매핑 url 패턴 , cors설정 객체
        return source;
    }

}