package com.ott.member;

import java.util.Optional;

import com.ott.entity.Member;

public interface MemberService {

	Member getMember(Member member);
	
	void modifyMember(Member member);
	
	  
}