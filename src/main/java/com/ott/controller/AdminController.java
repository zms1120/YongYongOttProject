package com.ott.controller;

import java.io.File;
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

import com.ott.entity.Episode;
import com.ott.entity.Movie;
import com.ott.entity.TVProgram;
import com.ott.episode.EpisodeService;
import com.ott.movie.MovieService;
import com.ott.tvprogram.TVProgramService;

@Controller
public class AdminController {

	@Autowired
	private MovieService movieService;
	@Autowired
	private TVProgramService tvProgramService;
	@Autowired
	private EpisodeService episodeService;
	
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


	// 업데이트 폼을 보여주는 페이지로 이동
	@GetMapping("/updateMovie")
	public String updateMovieForm(@RequestParam("movie_code") String movieCode, Model model) {
	    Movie movie = movieService.getMovieByCode(movieCode);

	    // 기존 이미지와 배너의 경로를 모델에 추가
	    model.addAttribute("originalImagePath", movie.getImage_path());
	    model.addAttribute("originalBannerPath", movie.getBanner_path());

	    model.addAttribute("movie", movie);
	    return "/layout/admin/updateMovie";
	}


	@PostMapping("/updateMovie")
	public String updateMovie(@ModelAttribute("movie") Movie movie,
	                          @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
	                          @RequestParam(value = "bannerFile", required = false) MultipartFile bannerFile,
	                          @RequestParam("originalImageFile") String originalImageFile,
	                          @RequestParam("originalBannerFile") String originalBannerFile) {
	    File directory = new File("src/main/resources/static/assets/images/movie");
	    if (!directory.exists()) {
	        directory.mkdirs();
	    }

	    // 이미지 파일이 선택되었을 경우에만 업데이트 수행
	    if (imageFile != null && !imageFile.isEmpty()) {
	        // 새 이미지 파일이 선택된 경우 처리
	        // ...
	    } else {
	        // 이미지 파일이 선택되지 않은 경우, 기존 이미지 파일명을 사용
	        movie.setImage_path(originalImageFile);
	    }

	    // 배너 파일도 동일하게 처리
	    if (bannerFile != null && !bannerFile.isEmpty()) {
	        // 새 이미지 파일이 선택된 경우 처리
	        // ...
	    } else {
	        // 이미지 파일이 선택되지 않은 경우, 기존 이미지 파일명을 사용
	        movie.setBanner_path(originalBannerFile);
	    }

	    try {
	        // MovieService를 사용하여 영화 정보 및 파일 업데이트
	        movieService.updateMovie(movie, imageFile, bannerFile);
	        // 업데이트된 영화의 상세 페이지로 리다이렉트
	        return "redirect:/getMovie?movie_code=" + movie.getMovie_code();
	    } catch (Exception e) {
	        // 예외 처리 (예: 로깅, 에러 페이지로 리다이렉트 등)
	        e.printStackTrace();
	        return "error";
	    }
	}

	// 업데이트 폼을 보여주는 페이지로 이동
		@GetMapping("/updateTVProgram")
		public String updateTVForm(@RequestParam("pseq") int pseq, Model model) {
		    TVProgram tvProgram = tvProgramService.getTVProgramByPseq(pseq);

		    // 기존 이미지와 배너의 경로를 모델에 추가
		    model.addAttribute("originalImagePath", tvProgram.getImage_path());
		    model.addAttribute("originalBannerPath", tvProgram.getBanner_path());

		    model.addAttribute("tvProgram", tvProgram);
		    return "/layout/admin/updateTV";
		}


		@PostMapping("/updateTVProgram")
		public String updateMovie(@ModelAttribute("tvProgram") TVProgram tvProgram,
		                          @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
		                          @RequestParam(value = "bannerFile", required = false) MultipartFile bannerFile,
		                          @RequestParam("originalImageFile") String originalImageFile,
		                          @RequestParam("originalBannerFile") String originalBannerFile) {
		    File directory = new File("src/main/resources/static/assets/images/tv");
		    if (!directory.exists()) {
		        directory.mkdirs();
		    }

		    // 이미지 파일이 선택되었을 경우에만 업데이트 수행
		    if (imageFile != null && !imageFile.isEmpty()) {
		        // 새 이미지 파일이 선택된 경우 처리
		        // ...
		    } else {
		        // 이미지 파일이 선택되지 않은 경우, 기존 이미지 파일명을 사용
		        tvProgram.setImage_path(originalImageFile);
		    }

		    // 배너 파일도 동일하게 처리
		    if (bannerFile != null && !bannerFile.isEmpty()) {
		        // 새 이미지 파일이 선택된 경우 처리
		        // ...
		    } else {
		        // 이미지 파일이 선택되지 않은 경우, 기존 이미지 파일명을 사용
		        tvProgram.setBanner_path(originalBannerFile);
		    }

		    try {
		        // MovieService를 사용하여 영화 정보 및 파일 업데이트
		        tvProgramService.updateTVProgram(tvProgram, imageFile, bannerFile);
		        // 업데이트된 영화의 상세 페이지로 리다이렉트
		        return "redirect:/getTVProgram?pseq=" + tvProgram.getPseq();
		    } catch (Exception e) {
		        // 예외 처리 (예: 로깅, 에러 페이지로 리다이렉트 등)
		        e.printStackTrace();
		        return "error";
		    }
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
    	 List<Episode> epiList = episodeService.getEpList(pseq);
    	 
        model.addAttribute("tvProgram", tvProgram);
        model.addAttribute("epiList", epiList);
        
        return "layout/admin/getTVProgram";
    }
	@GetMapping("/insertEpisode")
	public String insertEpisode(@RequestParam("pseq") int pseq, Model model) {
	    // pseq를 모델에 추가하여 Thymeleaf에서 사용할 수 있도록 함
	    model.addAttribute("pseq", pseq);
	    return "layout/admin/insertEpisode";
	}

	
	@PostMapping("/insertEpisode")
	public String insertEpisode(@ModelAttribute("episode") Episode episode, @RequestParam("pseq") int pseq) {
	    // pseq를 사용하여 TVProgram 객체를 가져옴
	    TVProgram tvProgram = tvProgramService.getTVProgramByPseq(pseq);
	    
	    // episode 객체에 TVProgram 설정
	    episode.setTvProgram(tvProgram);

	    // episode 객체를 이용하여 에피소드 정보 저장 로직 수행
	    episodeService.insertEpisode(episode);

	    // pseq 값을 가져와서 리다이렉트 URL에 포함시킴
	    pseq = episode.getTvProgram().getPseq();
	    
	    return "redirect:/getTVProgram?pseq=" + pseq;
	}


	
}
