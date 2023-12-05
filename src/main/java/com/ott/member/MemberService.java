package com.ott.member;


import com.ott.entity.Member;

public interface MemberService {

   // 회원 가입
   void joinMember(Member member);

   // 회원 정보 수정
   void modifyMember(Member member);

   // 회원 탈퇴
   void deleteMember(Member member);

   // 로그인
   Member getMember(Member member);
   
   // 아이디 및 비번 찾기
   String getEmailPhone(Member member);
   
   
   // 비번 찾기 핸드폰 번호와 이메일로 찾기
   String getIdPhone (Member member);
   


}