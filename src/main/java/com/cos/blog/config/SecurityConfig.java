package com.cos.blog.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.cos.blog.model.RespCM;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration // 메모리에 띄우기
@EnableWebSecurity // 필터 등록
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 그냥 만들어져 있는걸 쓴다

	// 해시 암호화 -> 복호화가 안 되기 떄문에 까먹으면 알 수가 없다 (데이터베이스 업데이트해서 바꿔야 된다)
	@Bean // @bean 사용 시 메서드에 붙일수 있다, static 사용 x
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();

	}

	@Override // 모든 주소 요청을 여기에서 받는다
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // 회원가입에 필요
		http.authorizeRequests() // 모든 req 받기
				.antMatchers("/user/profile/**", 
							"/post/write/**", 
							"/post/detail/**", 
							"/post/update/**",
							"/post/delete/**")
				.authenticated() // 주소 막기, 인증 필요
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") // 권한
				.anyRequest().permitAll() // 위 주소 말고는 인증 없이 접근 허용
			.and()
				.formLogin() // form 만들어서 로그인 하는 방식
				.loginPage("/user/login") // 인증 안 됐으면 가는 주소, get 방식
				.loginProcessingUrl("/user/login") // post 방식, post만 낚아챈다
				.successHandler(new AuthenticationSuccessHandler() {
					
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						
						System.out.println("성공함~~~~~~~~~~~~~~~~~~~");
						PrintWriter out = response.getWriter();
						
						ObjectMapper mapper = new ObjectMapper();
						  
						// string 으로 저장
						String jsonString = mapper.writeValueAsString(new RespCM(200,"ok"));
						System.out.println("jsonString : "+jsonString);
						out.print(jsonString);
						out.flush();
						
						
					}
				}); // defaultSuccessUrl을 사용할 수 있음.		
	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Override // 회원가입 시 암호화 된 비밀번호를 로그인 시 원래 비밀번호로 돌리는 것?
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encode());
		
	}

}
