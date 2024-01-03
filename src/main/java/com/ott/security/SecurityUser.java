package com.ott.security;

import java.util.Date;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.ott.entity.Member;

/*
 * JPA에서 조회한 Member 정보를 스프링 시큐리티에서
 * 사용할 UserDetails 타입으로 변환하는 클래스
 */
public class SecurityUser extends User {
	private static final long serialVersionUID = -5413550733371289936L;

	private Member member;

	// 생성자
	public SecurityUser(Member member) {
		// 조상의 생성자를 호출하여 스프링 시큐리티에 id, password, role, id 사용유무 전달

		// 암호화 처리
		super(member.getId(), member.getPassword(),
				AuthorityUtils.createAuthorityList(member.getPosition().toString()));
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

}
