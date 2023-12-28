package com.ott.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ott.entity.Member;
import com.ott.member.MemberService;
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
  
   // 로그아웃
   @GetMapping("/logout")
   public String logout(HttpSession session) {
      System.out.println("---> 로그아웃");
      // 세션 끝
      session.invalidate();

      // 메인 페이지로 이동
      return "layout/member/login";
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
   @PostMapping("/email_auth")
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
   @RequestMapping("/check_find_id")
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
         System.out.println("입력 아이디 " + id);
         System.out.println("입력 이메일 " + email);
         
         model.addAttribute("id", id);
         model.addAttribute("email", email);
         
         String searchUserPwd = memberService.getIdPhone(member);

         if (searchUserPwd != null) { // 비번을 찾은 경우
            System.out.println("비번 찾기 가능");
            System.out.println("비번 찾기 비번 : " + searchUserPwd);
            model.addAttribute("password", searchUserPwd);// 비번 값을 모델에 넣기
            
            return searchUserPwd;
            
         } else { // 아이디를 못찾은 경우
            System.out.println("비밀번호 찾을 수 없음");
            
            return "불가능";
         }
         
         
      }
   

   // 탈퇴
   @GetMapping("/delete")
   public String deleteAction(Member member, HttpSession session) {
      System.out.println("---> 회원 탈퇴");
      member= (Member) session.getAttribute("member");
      System.out.println(member.getId());
      System.out.println(member);   
      
      memberService.deleteMember(member);   

      //세션 종료! 얏호
      session.invalidate();
      // 로그인 페이지로
      return "layout/member/login";
   }
   
// 이용권 수정페이지 이동
   @GetMapping("/change")
   public String changeView(Member member) {
      System.out.println("---> 이용 변경 페이지로 가기");
      return "layout/member/change";
      
   }

   @PostMapping("/change")
   public String changeAction(Member member) {
      System.out.println("---> 이용 변경 내용 저장");      

      // 수정된 내용 저장
     
      memberService.changeMembership(member);
    
    	 
  
      return "redirect:/mypage";
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