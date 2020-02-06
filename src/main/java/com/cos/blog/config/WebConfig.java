package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

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

}
