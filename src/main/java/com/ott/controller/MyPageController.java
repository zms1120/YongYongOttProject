package com.ott.controller;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.ott.board.BoardService;
import com.ott.entity.Board;
import com.ott.entity.Member;
import com.ott.member.MemberService;
import com.ott.security.SecurityUser;
import com.util.member.MemberUtil;


@Controller
@ComponentScan(basePackages = {"com.util.member", "com.ott.controller"})
public class MyPageController {

   @Autowired
   private BoardService boardService;
   @Autowired
   private MemberService memberService;
   @Autowired
   private MemberUtil memberUtil;
   
   // 로그인 페이지로 이동
   @GetMapping("/login")
   public String loginView() {
       System.out.println("---> 로그인 페이지 이동");
       return "layout/member/login";
   }


   @GetMapping("mypage")
   public String myPage(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
       /*
	   // 현재 인증된 사용자의 정보 가져오기
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       // 인증된 사용자가 없다면 로그인 페이지로 리다이렉트
       if (authentication == null || !authentication.isAuthenticated()) {
           return "redirect:/login";
       }

       // 사용자의 Principal에서 UserDetails 추출
       Object principal = authentication.getPrincipal();
       */
       if (securityUser != null) {
          
           Member member = securityUser.getMember();
           model.addAttribute("member", member);

           // 게시판 목록 가져오기
           List<Board> myboard = boardService.findByIdBoardList(member.getId());
           model.addAttribute("myboard", myboard);

           // QnA 목록 가져오기
           List<Board> qna = boardService.findByMemberIdAndBCategory(member.getId(), "qna");
           model.addAttribute("myqna", qna);

           return "layout/member/mypage";
       } else {
           // principal이 SecurityUser를 구현하지 않은 경우
           // 예외 처리 또는 다른 방법으로 처리
           return "redirect:/login";
       }
   }

	// 회원정보 수정페이지 이동
	@GetMapping("modify")
	public String modifyView(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
		System.out.println("---> 회원 변경 페이지로 가기");

		// 세션에서 로그인한 사용자 정보 가져오기

		// 세션에 저장된 아이디 정보로 최신 회원 정보 불러오기
		Member member = securityUser.getMember();
		model.addAttribute("member", member);

		return "layout/member/modify";
	}

	@PostMapping("/modify")
	public String modifyAction(Member member, Authentication authentication) {
		System.out.println("---> 회원 변경 내용 저장");

		// 수정된 내용 저장
		memberService.modifyMember(member);

		// 세션 갱신
		String newEmail = member.getEmail(); // 변경된 회원 email
		String phoneNumber = member.getPhone_number();
		updateAuthenticationWithNewPosition(authentication, newEmail, phoneNumber);

		return "redirect:/mypage";
	}

	private void updateAuthenticationWithNewPosition(Authentication authentication, String newEmail,
			String phoneNumber) {
		// 사용자의 현재 인증 객체를 가져옴
		SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();

		// 사용자의 이용권 정보를 갱신
		securityUser.getMember().setEmail(newEmail);
		securityUser.getMember().setPhone_number(phoneNumber);

		// 여기에서는 SecurityContextHolder를 직접 조작하지 않고, Spring Security가 제공하는
		// Authentication 객체를 통해 간접적으로 세션을 갱신합니다.
	}

	@GetMapping("/datecheck")
	public String dateCheck(@AuthenticationPrincipal SecurityUser securityUser) {
		Member member = securityUser.getMember();
		member = memberUtil.checkPosition(member);
		memberService.changeMembership(member);
		return "redirect:/main";
	}

	@GetMapping("/loginerror")
	public String loginError(Model model) {
		model.addAttribute("message", "아이디 또는 비밀번호를 확인해주세요.");
		return "layout/member/login";
	}

}

