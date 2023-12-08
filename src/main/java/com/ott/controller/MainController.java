package com.ott.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ott.entity.Movie;
import com.ott.movie.MovieService;

@Controller
public class MainController {

	@Autowired
	private MovieService movieService;
	//메인 화면
	  @GetMapping("/main")
	    public String mainView(Model model, Movie movie) {
	        List<Movie> movieList = movieService.getMovieList(movie);
	        System.out.println(movieList);
	        model.addAttribute("movieList", movieList);
	        
	        return "layout/main";
	    }
}
