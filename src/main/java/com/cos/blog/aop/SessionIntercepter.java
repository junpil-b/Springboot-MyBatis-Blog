package com.cos.blog.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 백종원 = 어댑터 패턴
// @Controller, @Configulation, @Service, @Repository, @Component
// HandlerInterceptorAdapter / 어댑터패턴, 걸러준다 -> 필요한 것만 갖다 쓴다?
public class SessionIntercepter extends HandlerInterceptorAdapter{
		@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
// boolean 리턴 true면 실행 false면 튕겨내서 아무것도 실행 X
				HttpSession session = request.getSession();
				if(session.getAttribute("principal") == null) {
					System.out.println("인증 x");
					response.sendRedirect("/user/login");
					return false;
				}
				System.out.println("인증 o.");
				return true;
			}	
}
