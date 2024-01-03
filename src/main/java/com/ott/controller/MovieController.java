package com.ott.controller;


import com.ott.entity.Member;
import com.ott.entity.Movie;
import com.ott.member.MemberService;
import com.ott.movie.MovieService;
import com.ott.security.SecurityUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

//@RequestMapping("/layout/")
@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;
    
    @Autowired
    private MemberService memberService;

    // 영화 전체 목록
    @GetMapping("allmovie")
	public String findByGenreContaining(@RequestParam(name = "genre", required = false)String genre, Model model, Movie movie) {
		   	List<Movie> movieList = movieService.getMovieList(movie);
		    List<Movie> romanceList = movieService.findByKeywordContaining("로맨스");
		    List<Movie> fantasyList = movieService.findByKeywordContaining("판타지");
		    List<Movie> actionList = movieService.findByKeywordContaining("액션");
		    List<Movie> dramaList = movieService.findByKeywordContaining("드라마");
		    
		    model.addAttribute("romanceList", romanceList);
		    model.addAttribute("fantasyList", fantasyList);
		    model.addAttribute("actionList", actionList);
		    model.addAttribute("dramaList", dramaList);
		    model.addAttribute("movieList", movieList);
		    return "layout/contents/allmovie";
	}
    
    //영화 상세 보기
    @GetMapping("moviedetail")
    public String getMovie(Model model, @RequestParam("movie_code") String movie_code, @AuthenticationPrincipal SecurityUser securityUser) {
        // 새로운 Movie 객체를 생성하고 movieCode를 설정
    	Movie movie = new Movie();
    	
    	movie.setMovie_code(movie_code);
      
        // movieService.getMovie에 올바른 Movie 객체를 전달
        movie = movieService.getMovie(movie);
        // 동영상 경로 가져오기
        String videoPath = movieService.getVideoPath(movie_code);
        
        // 모델에 추가
        model.addAttribute("movie", movie);
        model.addAttribute("videoPath", videoPath);
        
      
	    if (securityUser == null) { 
	    	//로그인 한 사용자가 없다면
	    	model.addAttribute("member", new Member());
	    } else {
	    	// 세션에 저장된 아이디 정보로 최신 회원 정보 불러오기
			Member member = securityUser.getMember();
			//System.out.println("movieDetail: " + member.getId());
			model.addAttribute("member", member);
	    }
      
        return "layout/contents/moviedetail";
    }


	
}
