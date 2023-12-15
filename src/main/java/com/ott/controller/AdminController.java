package com.ott.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.ott.entity.Movie;
import com.ott.entity.TVProgram;
import com.ott.movie.MovieService;
import com.ott.tvprogram.TVProgramService;

@Controller
public class AdminController {

	@Autowired
	private MovieService movieService;
	@Autowired
	private TVProgramService tvProgramService;
	
	@GetMapping("/adminPage")
	public String adminMain() {
		return "layout/admin/adminPage";
	}
	@GetMapping("/contentsList")
	public String movieList(Movie movie, TVProgram tvProgram, Model model) {
		List<Movie>movieList = movieService.getMovieList(movie);
		List<TVProgram> tvProgramList = tvProgramService.getTVProgramList(tvProgram);
		
		model.addAttribute("movieList", movieList);
		model.addAttribute("tvProgramList", tvProgramList);
		return "layout/admin/contentsList";
	}

	@GetMapping("/insertMovie")
	public String insertMovieView() {
		return "layout/admin/insertmovie";
	}

	@PostMapping("/insertMovie")
	public String insertMovie(@ModelAttribute("movie") Movie movie,
	                          @RequestPart("imageFile") MultipartFile imageFile,
	                          @RequestPart("bannerFile") MultipartFile bannerFile) {
	    movieService.insertMovie(movie, imageFile, bannerFile);
	    return "redirect:/contentsList";
	}


	@GetMapping("/insertTVProgram")
	public String insertTVProgramView() {
		return "layout/admin/inserttvProgram";
	}

	@PostMapping("/insertTVProgram")
	public String insertTVProgram(@ModelAttribute("tvProgram") TVProgram tvProgram, @RequestPart("imageFile") MultipartFile imageFile,
            @RequestPart("bannerFile") MultipartFile bannerFile) {
		tvProgramService.insertTVProgram(tvProgram, imageFile, bannerFile);
		return "redirect:/contentsList";
	}

	@GetMapping("/updateMovie")
	public String updateMovieView() {
		return "layout/admin/insertmovie";
	}

	@PostMapping("/updateMovie")
	public void updateMovie(Movie movie, MultipartFile imageFile) {
		movieService.updateMovie(movie, imageFile);
	}

	@GetMapping("/updateTVProgram")
	public String updateTVProgramView() {
		return "layout/admin/inserttvProgram";
	}

	@PostMapping("/updateTVProgram")
	public void updateTVProgram(TVProgram tvProgram, MultipartFile imageFile) {
		tvProgramService.updateTVProgram(tvProgram, imageFile);
	}

	@PostMapping("/deleteMovie")
	public void deleteMovie(Movie movie) {
		movieService.deleteMovie(movie);
	}

	@PostMapping("/deleteTVProgram")
	public void deleteTVProgram(TVProgram tvProgram) {
		tvProgramService.deleteTVProgram(tvProgram);
	}
	
	@GetMapping("/getMovie")
    public String getMovie(Model model, @RequestParam("movie_code") String movie_code) {
        // 새로운 Movie 객체를 생성하고 movieCode를 설정
    	Movie movie = new Movie();
    	
    	movie.setMovie_code(movie_code);
      
        // movieService.getMovie에 올바른 Movie 객체를 전달
        movie = movieService.getMovie(movie);
        
        model.addAttribute("movie", movie);
        
        return "layout/admin/getMovie";
    }
	
	@GetMapping("/getTVProgram")
    public String getTVProgram(Model model, @RequestParam("pseq") int pseq) {
        // 새로운 Movie 객체를 생성하고 movieCode를 설정
    	TVProgram tvProgram = new TVProgram();
    	
    	tvProgram.setPseq(pseq);
      
        // movieService.getMovie에 올바른 Movie 객체를 전달
    	tvProgram = tvProgramService.getTVProgram(tvProgram);
        
        model.addAttribute("tvProgram", tvProgram);
        
        return "layout/admin/getTVProgram";
    }
}
