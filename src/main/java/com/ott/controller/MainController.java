package com.ott.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ott.entity.Movie;
import com.ott.entity.TVProgram;
import com.ott.movie.MovieService;
import com.ott.search.SearchService;

@Controller
public class MainController {

	@Autowired
	private MovieService movieService;
	private SearchService searchService;

	@Autowired
	public void MovieTvProgramController(SearchService searchService) {
		this.searchService = searchService;
	}

	// 메인 화면
	@RequestMapping({"/main","/"})
	public String mainView(Model model, Movie movie) {
		List<Movie> movieList = movieService.getMovieList(movie);
		System.out.println(movieList);
		model.addAttribute("movieList", movieList);
		
		// 임시 데이터!
		model.addAttribute("bannerList", movieList);

		return "layout/main";
	}

	@GetMapping("/search")
	public String searchByKeyword(@RequestParam(name = "searchKeyword") String keyword, Model model) {
		 List<Movie> movieByKeyword = searchService.movieByKeyword(keyword);
		 
		 List<TVProgram> tvProgramByKeyword = searchService.tvProgramByKeyword(keyword);
		 System.out.println(movieByKeyword);
		 System.out.println(tvProgramByKeyword);
		 model.addAttribute("movieResult", movieByKeyword);
		 model.addAttribute("tvProgramResult", tvProgramByKeyword);
		 
		 return "layout/contents/search";
	}

	@GetMapping("/layout/admin/accessDenied")
	   public void accessDenied() {
		   
	   }
}
