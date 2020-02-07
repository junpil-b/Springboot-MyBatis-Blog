package com.cos.blog.model.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqLoginDto {
	
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message="유저네임에 한글을 입력할 수 없다.")
	// 적힌 문자만 사용 가능? -> 한글 사용 불가, 정규표현식
	@Size(max=15, message ="유저네임의 길이가 잘못됐습니다.")
	@NotBlank(message = "유저네임을 입력하세요")
	private String username;
	
	@Size(max=15, message ="비밀번호의 길이가 잘못됐습니다.")
	@NotBlank(message = "비밀번호를 입력하세요")
	private String password;
}
