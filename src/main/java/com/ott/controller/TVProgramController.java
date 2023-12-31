package com.ott.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ott.entity.Episode;
import com.ott.entity.Member;
import com.ott.entity.TVProgram;
import com.ott.episode.EpisodeService;
import com.ott.member.MemberService;
import com.ott.security.SecurityUser;
import com.ott.tvprogram.TVProgramService;


@Controller
public class TVProgramController {

	@Autowired
	private TVProgramService tvProgramService;
	
	@Autowired
	private EpisodeService episodeService;
	
	@Autowired
    private MemberService memberService;

	// TVProgram 전체목록 
	@GetMapping("/tvprogram")
	public String findByCategoryContaining(@RequestParam(name = "p_category", required = false)String category, Model model, TVProgram tvProgram) {
		List<TVProgram> tvProgramList = tvProgramService.getTVProgramList(tvProgram);
		List<TVProgram> dramaList = tvProgramService.findByCategoryContaining("드라마");
		List<TVProgram> enterList = tvProgramService.findByCategoryContaining("예능");
		List<TVProgram> currentList = tvProgramService.findByCategoryContaining("시사교양");
		List<TVProgram> aniList = tvProgramService.findByCategoryContaining("애니");
		List<TVProgram> foreList = tvProgramService.findByCategoryContaining("해외시리즈");
		
		
		System.out.println(dramaList);
		System.out.println(enterList);
		System.out.println(currentList);
		System.out.println(aniList);
		System.out.println(foreList);
		
		model.addAttribute("tvProgramList", tvProgramList);
		model.addAttribute("dramaList", dramaList);
		model.addAttribute("enterList", enterList);
		model.addAttribute("currentList", currentList);
		model.addAttribute("aniList", aniList);
		model.addAttribute("foreList", foreList);
		
		return "layout/contents/alltv";
	}
	
	@GetMapping("tvdetail")
	public String getTVProgram(Model model, @RequestParam("pseq") int pseq, Episode episode, 
	                           @AuthenticationPrincipal SecurityUser securityUser) {
	    // 새로운 TVProgram 객체를 생성하고 qseq를 설정
	    TVProgram tvProgram = new TVProgram();
	    tvProgram.setPseq(pseq);

	    // tvProgarmService.getTVProgram에 올바른 TVProgram 객체를 전달
	    tvProgram = tvProgramService.getTVProgram(tvProgram);

	    // 동영상 경로 가져오기
	    String videoPath = tvProgramService.getVideoPath(pseq);
	    List<Episode> epiList = episodeService.getEpList(pseq);

	    // 모델에 추가
	    model.addAttribute("tvProgram", tvProgram);
	    model.addAttribute("videoPath", videoPath);
	    model.addAttribute("epiList", epiList);

	    // Spring Security를 사용하여 인증된 사용자 정보 가져오기
	   
	    if (securityUser != null) {
	        Member member = securityUser.getMember();
	        model.addAttribute("member", member);
	    } else {
	        // 사용자가 인증되어 있지 않을 경우
	        model.addAttribute("member", new Member());
	    }

	    return "layout/contents/tvdetail";
	}

}
