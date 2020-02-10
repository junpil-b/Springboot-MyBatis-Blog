package com.cos.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.model.RespCM;
import com.cos.blog.model.ReturnCode;
import com.cos.blog.model.user.User;
import com.cos.blog.model.user.dto.ReqJoinDto;
import com.cos.blog.model.user.dto.ReqLoginDto;
import com.cos.blog.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private HttpSession session;

	@GetMapping("/user/join")
	public String join() {
		return "/user/join";
	}

	@GetMapping("/user/login")
	public String login() {
		return "/user/login";
	}

	@GetMapping("/user/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
// 기억하기, 데이터 없이 그냥 이동		
	}

	// 인증, 작성자 확인
	@GetMapping("/user/profile/{id}")
	public String profile(@PathVariable int id) {

		User principal = (User) session.getAttribute("principal");

		if (principal.getId() == id) { // 로그인 성공
				return "/user/profile";
			} else {
				return "/user/login"; // 작성자 X, 권한 X
			}
		
		}
	

	// 메세지 컨버터는 req 받을 때 setter 호출
	@PostMapping("/user/join")
	public ResponseEntity<?> join(@Valid @RequestBody ReqJoinDto dto, BindingResult bindingResult) {
		// BindingResult는 null 또는 공백처리?

		int result = userService.회원가입(dto);

		if (result == ReturnCode.아이디중복) {
			return new ResponseEntity<RespCM>(new RespCM(ReturnCode.아이디중복, "아이디중복"), HttpStatus.OK);

		} else if (result == ReturnCode.성공) {
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);

		} else {
			return new ResponseEntity<RespCM>(new RespCM(500, "fail"), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@PostMapping("/user/login")
	public ResponseEntity<?> login(@Valid @RequestBody ReqLoginDto dto, BindingResult bindingResult) {
		// req 검증 필요 -> aop로 처리

		// 응답 시 서비스 호출
		User principal = userService.로그인(dto);
		
		if (principal != null) {
			session.setAttribute("principal", principal);
			return new ResponseEntity<RespCM>(new RespCM(200, "ok"), HttpStatus.OK);
		} else {
			return new ResponseEntity<RespCM>(new RespCM(400, "fail"), HttpStatus.BAD_REQUEST);
		}
	}
}
