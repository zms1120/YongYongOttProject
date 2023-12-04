package com.ott.controller;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.ott.board.BoardService;
import com.ott.entity.Board;
import com.ott.entity.Member;
import com.ott.member.MemberService;


@Controller
public class MyPageController {

   @Autowired
   private BoardService boardService;
   @Autowired
   private MemberService memberService;
   
// 로그인
   @GetMapping("/layout/login")
   public void loginView() {
      System.out.println("---> 로그인 페이지 이동");
   }

   // 로그인 session에 로그인 유지
   @PostMapping("/layout/login")
   public String loginAction(Member member, Model model, HttpSession session) {
       System.out.println("---> 로그인 내용 받기");

       // 로그인 실패 시 null이 아닌 빈 Member 객체를 생성하여 전달
       if (member == null) {
           member = new Member();
       }

       Member findMember = memberService.getMember(member);

       if (findMember != null && findMember.getPassword().equals(member.getPassword())) {
           // 로그인 인증 성공(있는 고객)
           model.addAttribute("member", findMember);

           // 세션에 사용자 정보 저장
           session.setAttribute("member", findMember);

           System.out.println("로그인 이름 : " + findMember.getName());
           System.out.println("로그인 아이디 : " + findMember.getId());

           // 로그인 성공했으니 메인으로 이동
           return "redirect:/mypage";

       } else {// 로그인 실패(없는 고객)
           System.out.println("로그인 실패이름 : " + member.getId());
           System.out.println("로그인 실패아이디 : " + member.getPassword());
           System.out.println(findMember);

           return "redirect:/layout/login";
       }
   }

   
   @GetMapping("/mypage")
   public String myPage(Model model, Board board, Member member, HttpSession session) {
       // 세션에서 로그인한 사용자 정보 가져오기
       Member loggedInMember = (Member) session.getAttribute("member");

       // 로그인한 사용자가 없다면 로그인 페이지로 리다이렉트
       if (loggedInMember == null) {
           return "redirect:/layout/login";
       }

       // 로그인한 사용자의 ID로 Member 객체를 가져옴
       member = memberService.getMember(loggedInMember);

       // 가져온 Member 객체를 모델에 추가
       model.addAttribute("member", member);

       // 게시판 목록 가져오기
       List<Board> boardList = boardService.findByMemberIdAndAllBCategory(member.getId());
       model.addAttribute("myboard", boardList);

       // QnA 목록 가져오기
       List<Board> qna = boardService.findByMemberIdAndBCategory(member.getId(), "qna");
       model.addAttribute("myqna", qna);

       return "layout/mypage";
   }
   
// 회원정보 수정페이지 이동
   @GetMapping("/layout/modify")
   public void modifyView() {
      System.out.println("---> 회원 변경 페이지로 가기");
   }

   @PostMapping("/layout/modify")
   public String modifyAction(Member member) {
      System.out.println("---> 회원 변경 내용 저장");

      // 수정된 내용 저장
      memberService.modifyMember(member);

      System.out.println("가입일" + member.getReg_date());
      System.out.println("끝" + member.getEnd_date());
      System.out.println("갱신" + member.getRenew_date());

      // 메인페이지로 리다이렉트
      return "redirect:/mypage";
   }

	/*
	@GetMapping("/mypage")
	   public String mypageView(Model model) {
	      
	      Member member = new Member();
	      member.setId("member123");
	      member.setPassword("1111");
	      member.setEmail("member123@gmail.com");
	      member.setName("정민수");
	      member.setPhone_number("010-1234-1234");
	      member.setPosition("BASIC");
	      
	      Date today = new Date();
	      
	      member.setReg_date(today);
	      member.setRenew_date(today);
	      
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(today);
	      calendar.add(Calendar.MONTH, 1);
	      member.setEnd_date(calendar.getTime());
	      
	      model.addAttribute("member", member);
	     
	      Board board = new Board();
	      board.setB_seq(1);
	      board.setTitle("새글");
	      board.setWrite_date(today);
	      model.addAttribute("myboard", board);
	      
	      Board qna = new Board();
	      
	      qna.setB_seq(2);
	      qna.setTitle("새문의글");
	      qna.setWrite_date(today);
	      
	      
	      model.addAttribute("myqna", qna);
	      
	      return "layout/mypage";
	   }
	   */

}

