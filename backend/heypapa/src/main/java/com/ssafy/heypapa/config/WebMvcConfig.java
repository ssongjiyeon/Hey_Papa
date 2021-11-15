package com.ssafy.heypapa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	private final String heyPapaImagesPath;
	
	public WebMvcConfig(@Value("${custom.path.heypapa-images}") String heyPapaImagesPath) {
		this.heyPapaImagesPath = heyPapaImagesPath;
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    		registry.addResourceHandler("/resources/**")
    				.addResourceLocations("/WEB-INF/resources/");
    		
    		registry.addResourceHandler("swagger-ui.html")
    				.addResourceLocations("classpath:/META-INF/resources/");

    		registry.addResourceHandler("/webjars/**")
    				.addResourceLocations("classpath:/META-INF/resources/webjars/");
    		
    	// Front-end에서 참조하는 URL을 /dist로 매핑
        registry.addResourceHandler("/css/**")
        			.addResourceLocations("classpath:/dist/css/");
        	registry.addResourceHandler("/fonts/**")
        			.addResourceLocations("classpath:/dist/fonts/");
        registry.addResourceHandler("/icons/**")
				.addResourceLocations("classpath:/dist/icons/");
        registry.addResourceHandler("/img/**")
			.addResourceLocations("classpath:/dist/img/");
        registry.addResourceHandler("/js/**")
				.addResourceLocations("classpath:/dist/js/");
        
        // 이미지 저장 경로 설정
        List<String> imageFolders = Arrays.asList("user", "article");
        for(String imageFolder : imageFolders) {
        	registry.addResourceHandler("/static/img/" + imageFolder + "/**")
        		.addResourceLocations("file:///" + heyPapaImagesPath + imageFolder + "/")
        		.setCachePeriod(3600)
        		.resourceChain(true)
        		.addResolver(new PathResourceResolver());
        	
        }
    }

    public Filter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }

    @Bean
    public FilterRegistrationBean loggingFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(requestLoggingFilter());
        registration.addUrlPatterns("/api/*");
        return registration;
    }
}
