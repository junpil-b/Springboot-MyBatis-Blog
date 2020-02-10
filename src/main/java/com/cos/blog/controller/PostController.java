package com.cos.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.blog.model.user.User;

@Controller
public class PostController {

	@Autowired
	// 인증 확인
	private HttpSession session;

	@GetMapping({ "", "/", "/post" })
	public String posts() {
		// ViewResolver 관여
		return "/post/list";
	}

	@GetMapping("/post/{id}")
	public String post() {
		return "/post/detail";
	}

	// 인증 체크 -> 세션이 있는지만 확인
	@GetMapping("/post/write")
	public String write() {
		return "/post/write";
	}

	// 인증, 작성자 확인
	@GetMapping("/post/update/{id}")
	public String update(@PathVariable int postId, @RequestParam int userId) {
		User principal = (User) session.getAttribute("principal");
// postId를 select 해서 post 가져오기 필요 -> model에 담기 필요		

		if (principal.getId() == userId) {
			return "/post/login";
		}
		return "/user/update";

	}
}
