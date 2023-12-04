package com.ott.member;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.ott.entity.Member;
import com.ott.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	
	
	// 회원 정보 수정
	   @Override
	   public void modifyMember(Member member) {
	      // 아이디로
	      Member findMember = memberRepository.findById(member.getId()).get();
	      
	      System.out.println("아이디" + member.getId());

	      // 비번, 이름, 핸드폰번호, 회원구분 변경 가능
	      findMember.setPassword(member.getPassword());
	      findMember.setName(member.getName());
	      findMember.setPhone_number(member.getPhone_number() );
	      findMember.setPosition(member.getPosition());

	      memberRepository.save(findMember);
	   }
	   
	   @Override
		public Member getMember(Member member) {
			return memberRepository.findById(member.getId()).get();
		}
	   
	   
		
	
}
