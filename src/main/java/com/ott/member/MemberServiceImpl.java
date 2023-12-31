package com.ott.member;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.ott.entity.Member;
import com.ott.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
   @Autowired
   MemberRepository memberRepository;
  
   private final PasswordEncoder passwordEncoder;

   public MemberServiceImpl(PasswordEncoder passwordEncoder) {
       this.passwordEncoder = passwordEncoder;
   }

   
   // 회원 가입
   @Override
   public void joinMember(Member member) {
       System.out.println("가입 작동");

       // 사용자로부터 받은 비밀번호를 암호화
       String encodedPassword = passwordEncoder.encode(member.getPassword());
       // 암호화된 비밀번호를 다시 설정
       member.setPassword(encodedPassword);

       // 회원 저장
       memberRepository.save(member);
   }

   // 회원 정보 수정
   public void modifyMember(Member member) {
       // 아이디로 회원 조회
       Member findMember = memberRepository.findById(member.getId()).orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

       System.out.println("아이디: " + member.getId());

       // 비밀번호가 변경된 경우에만 암호화하여 저장
       String newPassword = member.getPassword();
       if (newPassword != null && !newPassword.isEmpty()) {
           String encodedPassword = passwordEncoder.encode(newPassword);
           findMember.setPassword(encodedPassword);
       }

       // 나머지 정보 업데이트
       findMember.setName(member.getName());
       findMember.setPhone_number(member.getPhone_number());
       findMember.setPosition(member.getPosition());

       // 변경된 회원 정보 저장
       memberRepository.save(findMember);
   }

   // 회원 탈퇴
   @Override
   public void deleteMember(Member member) {
      Optional<Member> findMember = memberRepository.findById(member.getId());
      System.out.println("로그인시 정보 확인 : " + findMember);      
      
      memberRepository.delete(member);
   }

   // 로그인시 아이디 확인
   @Override
   public Member getMember(Member member) {
      Optional<Member> findMember = memberRepository.findById(member.getId());

      if (findMember.isPresent()) { // 아이디가 있을때.
         System.out.println("로그인시 정보 확인 : " + findMember);
         return findMember.get(); // 아이디 정보 빼오기
      } else {
         return null;
      }
   }

   // 아이디 찾기 - 핸드폰 번호와 이메일로
   @Override
   public String getEmailPhone(Member member) {
      Member searchId = memberRepository.findByEmailAndPhone_number(member.getEmail(), member.getPhone_number());

      if (searchId != null) { // 이메일과 핸드폰번호로 아이디를 찾은 경우
         return searchId.getId();
      } else { // 아이디를 못찾은 경우
         return null;
      }
   }

   // 비번 찾기 -아이디와 이메일로
   @Override
   public String getIdPhone(Member member) {
      Member searchPwd = memberRepository.findByIdAndEmail(member.getId(), member.getEmail());
      System.out.println("비번 찾기 아이디 입니다. " + member.getId());
      System.out.println("비번 찾기 이메일 입니다. " + member.getEmail());

      if (searchPwd != null) { // 아이디와 이메일로 비번을 찾은 경우
         System.out.println("비번은? :" + searchPwd.getPassword());
         return searchPwd.getPassword();
      } else { // 비번을 못찾은 경우
         return null;
      }

   }
   
// 이용권 수정
   @Override
   public void changeMembership (Member member) {
      // 아이디로
      Member findMember = memberRepository.findById(member.getId()).get();

      System.out.println("아이디" + member.getId());

      // 포지션, 갱신 구입날짜, 이용권 종료 날짜만 수정가능
      findMember.setPosition(member.getPosition());
      findMember.setRenew_date(member.getRenew_date()); 
      findMember.setEnd_date(member.getEnd_date());

      memberRepository.save(findMember);
   }

   @Override
   public List<Member> getMemberList(Member member) {
      
      return (List<Member>) memberRepository.findAll();
   }
}