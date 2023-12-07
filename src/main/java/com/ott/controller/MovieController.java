package com.ott.controller;


import com.ott.entity.Movie;
import com.ott.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

//@RequestMapping("/layout/")
@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/main")
    public String mainView(Model model, Movie movie) {
        List<Movie> movieList = movieService.getMovieList(movie);
        System.out.println(movieList);
        model.addAttribute("movieList", movieList);
        
        return "layout/main";
    }

    
    @GetMapping("detail")
    public String getMovie(Model model, @RequestParam("movie_code") String movie_code) {
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
      

        return "layout/detail";
    }
    @GetMapping("/insertMovie")
	public void insertMovieView() {
		
		
	}

	
	@PostMapping("/insertMovie")
	@Transactional
	public String insertMovieAction(Movie movie) {
		
		
		movieService.insertMovie(movie);
		
		return "redirect:getMovieList";
			
	}
	
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
		    return "layout/allmovie";
	}
}
