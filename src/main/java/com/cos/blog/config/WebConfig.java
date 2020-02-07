package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.cos.blog.aop.SessionIntercepter;

// 웹 설정
// web.xml을 자바로 바꾼것
// 외부 폴더 이미지 등을 읽어온다?
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Value("$(file.path)")
	private String fileReadPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
// 파일 경로 인식
		
		registry.addResourceHandler("/media/**")
		.addResourceLocations("file:///" + fileReadPath)
		.setCachePeriod(3600)
		.resourceChain(true)
		.addResolver(new PathResourceResolver());
	}
	
// 인터셉터	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	
		registry.addInterceptor(new SessionIntercepter())
// new 안하고 DI해도 된다
// 밑에 주소에 걸리면  SessionIntercepter() 여기로 간다
			.addPathPatterns("/user/profile/**")
			.addPathPatterns("/post/write/**")
			.addPathPatterns("/post/update/**")
			.addPathPatterns("/post/delete/**");
		
// addPathPatterns 제외시킬 때 사용
		
	}

}
