package com.cos.blog.model.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// MaBatis에서 ResultType으로 담을 때 생성자 혹은 Setter중 무엇이 호출되는지 확인 후 Lombok 변경
@Data
@NoArgsConstructor
public class User implements UserDetails {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String profile;
	private Timestamp createDate;
	private String role; // USER, MANAGER, ADMIN
	
	@Builder
	public User(String username, String password, String email, String profile, String role) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile = profile;
		this.role = role;
	}
	
// username과 password의 getter도 만들어져야 하지만 이미 필드명으로 있기 떄문에 생성 안 됐다
	
	@Override // 여러개의 권한
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub

		// 한 개의 권한
		Collection<SimpleGrantedAuthority> collertors = new ArrayList<>();
		collertors.add(new SimpleGrantedAuthority("ROLE_"+role));
		// role만 넣으면 스프링이 인식을 못 하기에 규칙을 따른다
		return collertors;
	}

	@Override // 계정 만료 되었는지 확인 후 리턴 true면 유효 false면 만료 
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override // 계정이 잠겨있는지 확인 true면 유효 false면 잠긴 계정(비밀번호 몇 번 틀릴시 잠김 설정)
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override // 비밀번호가 만료 되었는지 확인 true면 유효 false면 만료
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override // 해당 계정이 활성화 되어 있는지 확인 true면 활성화
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	

}
