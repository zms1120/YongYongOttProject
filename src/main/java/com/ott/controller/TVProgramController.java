package com.ott.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ott.entity.TVProgram;
import com.ott.tvprogram.TVProgramService;

//@RequestMapping("/tvprogram")
@Controller
public class TVProgramController {

	@Autowired
	private TVProgramService tvProgramService;

	@GetMapping("/TVProgramList")
	public String getAllTVPrograms(Model model, TVProgram tvProgram) {
		List<TVProgram> tvProgramList = tvProgramService.getTVProgramList(tvProgram);
		model.addAttribute("tvProgramList", tvProgramList);

		return "tvprogram/TVProgramList";
	}
	
	  @GetMapping("/tvdetail")
	    public String getTVProgram(Model model, @RequestParam("pseq") int pseq) {
	        // 새로운 Movie 객체를 생성하고 movieCode를 설정
	         TVProgram tvProgram = new TVProgram();
	         tvProgram.setPseq(pseq);
	        
	        // movieService.getMovie에 올바른 Movie 객체를 전달
	        tvProgram = tvProgramService.getTVProgram(tvProgram);
	        // 동영상 경로 가져오기
	        String videoPath = tvProgramService.getVideoPath(pseq);
	        
	        // 모델에 추가
	        model.addAttribute("tvProgram", tvProgram);
	        model.addAttribute("videoPath", videoPath);
	        
	        return "layout/tvdetail";
	  }
	@PostMapping("/insertTVProgram")
	public String insertTVProgram(@ModelAttribute TVProgram tvProgram, @RequestParam("imageFile") MultipartFile imageFile) {
	    tvProgramService.insertTVProgram(tvProgram, imageFile);
	    return "redirect:/tvprogram/TVProgramList";
	}

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
		
		return "layout/alltv";
	}
}
