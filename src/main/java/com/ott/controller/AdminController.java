package com.ott.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/insertMovie")
	public String insertMovieView() {
		return "layout/admin/insertmovie";
	}
	@PostMapping("/insertMovie")
	public void insertMovie(Movie movie, MultipartFile imageFile) {
		movieService.insertMovie(movie, imageFile);
	}
	@GetMapping("/insertTVProgram")
	public String insertTVProgramView() {
		return "layout/admin/inserttvProgram";
	}
	@PostMapping("/insertTVProgram")
	public void insertTVProgram(TVProgram tvProgram, MultipartFile imageFile) {
		tvProgramService.insertTVProgram(tvProgram, imageFile);
	}
	@GetMapping("/updateMovie")
	public String updateMovieView() {
		return "layout/admin/insertmovie";
	}
	@PostMapping("/updateMovie")
	public void updateMovie(Movie movie, MultipartFile imageFile) {
		movieService.insertMovie(movie, imageFile);
	}
	@GetMapping("/updateTVProgram")
	public String updateTVProgramView() {
		return "layout/admin/inserttvProgram";
	}
	@PostMapping("/updateTVProgram")
	public void updateTVProgram(TVProgram tvProgram, MultipartFile imageFile) {
		tvProgramService.insertTVProgram(tvProgram, imageFile);
	}
	@PostMapping("/deleteMovie")
	public void deleteMovie(Movie movie) {
		movieService.deleteMovie(movie);
	}
	@PostMapping("/deleteTVProgram")
	public void deleteTVProgram(TVProgram tvProgram) {
		tvProgramService.deleteTVProgram(tvProgram);
	}
}
