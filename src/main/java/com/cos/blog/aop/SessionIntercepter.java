package com.cos.blog.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionIntercepter extends HandlerInterceptorAdapter {
// HandlerInterceptorAdapter / 어댑터패턴, 걸러준다 -> 필요한 것만 갖다 쓴다?

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
// boolean 리턴 true면 실행 false면 튕겨내서 아무것도 실행 X

		HttpSession session = request.getSession();
		if (session.getAttribute("principal") == null) {
			System.out.println("인증 x 나가");
			response.sendRedirect("/user/login");
			return false;

		}
		System.out.println("인증 O");
		return true;

	}

}
