package com.ott.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ott.entity.Member;
import com.ott.member.MemberService;
import com.ott.security.SecurityUser;
import com.util.mail.MailDTO;
import com.util.mail.MailSender;

@Controller
@ComponentScan(basePackages = {"com.util.mail", "com.ott.controller"})
public class MemberController {

   @Autowired
   private MemberService memberService;
   @Autowired
   private MailSender mailSender;
   

   // 회원가입 페이지 출력 요청
   @GetMapping("/join")
   public String joinView() {
      System.out.println("---> 회원 가입 페이지 이동");
      
      return "layout/member/join";

   }

   // 회원가입
   @PostMapping("/join")
   public String joinMember(Member member) {
      System.out.println("---> 회원 가입 완료!!");

      memberService.joinMember(member);

      // 로그인페이지로 이동
      return "redirect:/login";
   }
  
  

   // 아이디 중복 페이지
   // ajax 연결
   @RequestMapping("/check_id")
   @ResponseBody
   public String idCheckView(@RequestParam(name = "id") String id, Model model, Member member) {
      System.out.println("---> 아이디 중복 페이지");
      System.out.println("입력값 " + id);
      model.addAttribute("id", id);

      Member searchId = memberService.getMember(member);
      System.out.println("member.getId() : " + member.getId());

      if (searchId == null && member.getId() != "") {   // 사용 가능한 경우
         System.out.println("사용 가능");
         return "사용가능";
      } else { // 사용 불가능한 경우
         System.out.println("사용 불가능");
         return "불가능";
      }
   }
   
   // 메일 인증 기능
   // ajax 연결
   @ResponseBody
   @GetMapping("/email_auth")
   public String MailSend(@RequestParam("email") String email){

	   System.out.println("mail: " + email);
	   
       MailDTO mailDto = new MailDTO(email);
       System.out.println(mailDto.getNumber());
       
       mailSender.mailSending(mailDto);
        
	   return mailDto.getNumber() + "";
   }

   // 아이디 찾기 이동
   @GetMapping("/find_id")
   public String findIdCheckView() {
      System.out.println("---> 아이디 찾기 페이지 이동");
      return "layout/member/find_id";
      
   }
   // 아이디 찾기 이메일로 핸드폰 번호로 찾기
   @ResponseBody
   @GetMapping("/check_find_id")
   public String findIdView(@RequestParam(name = "email") String email,
         @RequestParam(name = "phone_number") String phone_number, Model model, Member member) {
      System.out.println("---> 아이디 찾기 시작!");
      System.out.println("입력 핸드폰 번호 " + phone_number);
      System.out.println("입력 이메일 " + email);
      
      
      model.addAttribute("phone_number", phone_number);
      model.addAttribute("email", email);

      String searchUserId = memberService.getEmailPhone(member);


      if (searchUserId != null) { // 아이디를 찾은 경우
         System.out.println("아이디 찾기 가능");
         System.out.println("아이디 찾기 아이디 : " + searchUserId);
         model.addAttribute("id", searchUserId);// 아이디 값을 모델에 추가
         
         return searchUserId;
         
      } else { // 아이디를 못찾은 경우
         System.out.println("없는 아이디");
         
         return "불가능";
      }
      
   }
   
   // 비번 찾기 찾기 이동
      @GetMapping("/find_pwd")
      public String findPwdCheckView() {
    	  System.out.println("---> 비번 찾기 페이지 이동");
         
         return "layout/member/find_pwd";
      }
      
      // 비번 찾기 아이디와 이메일로 비밀번호로 찾기
      @ResponseBody
      @RequestMapping("/check_find_pwd")
      public String findpwdView(@RequestParam(name = "id") String id,
            @RequestParam(name = "email") String email, Model model, Member member) {
         System.out.println("---> 비번 찾기 오픈 페이지");
         
         String searchUserPwd = memberService.getIdPhone(member);

         if (searchUserPwd != null) { // 비번을 찾은 경우
            return id;
            
         } else { // 아이디를 못찾은 경우
        	    System.out.println("비밀번호 찾을 수 없음");
        	    return "불가능";
         }
       
      }
      
      @GetMapping("/renew_pwd")
      public String renewPwdView(@RequestParam(name = "id") String id, Model model) {
    	 System.out.println(id);
         model.addAttribute("id", id);
         return "layout/member/renew_pwd";
      }
      
      // 비밀번호 재설정
      @ResponseBody
      @RequestMapping("/renew_pwd_update")
      public String renewPwdAction(@RequestParam(name = "id") String id, @RequestParam(name = "password") String password) {
         System.out.println("renew_pwd_update");
         Member member = new Member();
         member.setId(id);
         
         Member renew = memberService.getMember(member);
         
         renew.setPassword(password);
         memberService.modifyMember(renew);
         
         return "success";
      }
   

   // 탈퇴
      @GetMapping("/delete")
      public String deleteAction(HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal SecurityUser securityUser) {
          System.out.println("---> 회원 탈퇴");
          Member member = securityUser.getMember();
          System.out.println(member.getId());
          System.out.println(member);

          // 회원 삭제 처리
          memberService.deleteMember(member);

          // Spring Security에서 제공하는 로그아웃 핸들러 사용
          Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          if (auth != null) {
              new SecurityContextLogoutHandler().logout(request, response, auth);
          }

          // 세션 무효화
          request.getSession().invalidate();

          // 로그인 페이지로 리다이렉트
          return "redirect:/login";
      }
// 이용권 수정페이지 이동
      @GetMapping("/change")
      public String changeView(Model model) {
          System.out.println("---> 이용 변경 페이지로 가기");

          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

          // 사용자가 인증되어 있지 않다면, 로그인하지 않은 상태로 이용권 페이지 열기
          if (authentication == null || !authentication.isAuthenticated()) {
              return "layout/member/change";
          }

          // 사용자가 인증되어 있다면, 사용자 정보를 가져와서 모델에 추가
          Object principal = authentication.getPrincipal();
          if (principal instanceof SecurityUser) {
              SecurityUser securityUser = (SecurityUser) principal;
              Member member = securityUser.getMember();
              model.addAttribute("member", member);
          }

          // 캐시를 비활성화하기 위해 "no-cache" 헤더를 추가하지 않고 바로 템플릿 리턴
          return "layout/member/change";
      }


      @PostMapping("/change")
      public String changeAction(Member member, Authentication authentication) {
         System.out.println("---> 이용 변경 내용 저장");      

         // 수정된 내용 저장
         memberService.changeMembership(member);

         // 세션 갱신
         String newPosition = member.getPosition(); // 변경된 이용권 정보
         updateAuthenticationWithNewPosition(authentication, newPosition);

         return "redirect:/mypage";
      }

      private void updateAuthenticationWithNewPosition(Authentication authentication, String newPosition) {
         // 사용자의 현재 인증 객체를 가져옴
         SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();

         // 사용자의 이용권 정보를 갱신
         securityUser.getMember().setPosition(newPosition);

         // 여기에서는 SecurityContextHolder를 직접 조작하지 않고, Spring Security가 제공하는
         // Authentication 객체를 통해 간접적으로 세션을 갱신합니다.
      }


   //이용약관 관련 메소드
   @GetMapping("/age-restriction")
	public String ageRestriction() {
		return "layout/member/terms/age-restriction";
	}
	@GetMapping("/service")
	public String service() {
		return "layout/member/terms/service";
	}
	@GetMapping("/member_privacy")
	public String memberPrivacy() {
		return "layout/member/terms/member_privacy";
	}
	@GetMapping("/member_payment")
	public String memberPayment() {
		return "layout/member/terms/member_payment";
	}
	@GetMapping("/member_event")
	public String memberEvent() {
		return "layout/member/terms/member_event";
	}
	@GetMapping("/member_ad")
	public String memberAdvertise() {
		return "layout/member/terms/member_ad";
	}
	@GetMapping("/member_target")
	public String memberTarget() {
		return "layout/member/terms/member_target";
	}
}