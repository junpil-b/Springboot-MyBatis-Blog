package com.cos.blog.repository;

import com.cos.blog.model.user.User;
import com.cos.blog.model.user.dto.ReqJoinDto;
import com.cos.blog.model.user.dto.ReqLoginDto;

public interface UserRepository {
	// 데이터를 dto로 받는다
	int save(ReqJoinDto dto); 
	int findByUsername(String username);
	User findByUsernameAndPassword(ReqLoginDto dto);
}
