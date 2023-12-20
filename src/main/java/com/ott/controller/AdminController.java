package com.ott.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.ott.board.BoardService;
import com.ott.entity.Board;
import com.ott.entity.Episode;
import com.ott.entity.Member;
import com.ott.entity.Movie;
import com.ott.entity.TVProgram;
import com.ott.episode.EpisodeService;
import com.ott.member.MemberService;
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
	@Autowired
	private BoardService boardService;
	@Autowired
	private MemberService memberService;

	@GetMapping("/adminPage")
    public String adminMain(Movie movie, TVProgram tvProgram,Member member,Board board, Model model) {
       
    List<Board> boardList = boardService.getBoardList(board);
    List<Member> memberList = memberService.getMemberList(member);
    List<Movie> movieList = movieService.getMovieList(movie);
     List<TVProgram> tvProgramList = tvProgramService.getTVProgramList(tvProgram);
     
     model.addAttribute("boardList", boardList);
     model.addAttribute("memberList", memberList);         
     model.addAttribute("movieList", movieList);
     model.addAttribute("tvProgramList", tvProgramList);
       
     return "layout/admin/adminPage";
    }
	@GetMapping("/getMovieList")
	public String movieList(Movie movie, Model model) {
		List<Movie> movieList = movieService.getMovieList(movie);

		model.addAttribute("movieList", movieList);

		return "layout/admin/getMovieList";
	}

	@GetMapping("/getTVProgramList")
	public String movieList(TVProgram tvProgram, Model model) {

		List<TVProgram> tvProgramList = tvProgramService.getTVProgramList(tvProgram);

		model.addAttribute("tvProgramList", tvProgramList);
		return "layout/admin/getTVProgramList";
	}

	@GetMapping("/insertMovie")
	public String insertMovieView() {
		return "layout/admin/insertmovie";
	}

	@PostMapping("/insertMovie")
	public String insertMovie(@ModelAttribute("movie") Movie movie, @RequestPart("imageFile") MultipartFile imageFile,
			@RequestPart("bannerFile") MultipartFile bannerFile) {
		movieService.insertMovie(movie, imageFile, bannerFile);
		return "redirect:/getMovieList";
	}

	@GetMapping("/insertTVProgram")
	public String insertTVProgramView() {
		return "layout/admin/inserttvProgram";
	}

	@PostMapping("/insertTVProgram")
	public String insertTVProgram(@ModelAttribute("tvProgram") TVProgram tvProgram,
			@RequestPart("imageFile") MultipartFile imageFile, @RequestPart("bannerFile") MultipartFile bannerFile) {
		tvProgramService.insertTVProgram(tvProgram, imageFile, bannerFile);
		return "redirect:/getTVProgramList";
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

	@GetMapping("/deleteMovie/{movie_code}")
	public String deleteMovie(@PathVariable("movie_code") String movie_code) {


		movieService.deleteMovieByMovieCode(movie_code);
		return "redirect:/getMovieList";
	}

	@GetMapping("/deleteTVProgram/{pseq}")
	public String deleteTVProgram(@PathVariable("pseq")int pseq) {
		tvProgramService.deleteTVProgramByPseq(pseq);
		
		return "redirect:/getTVProgramList";
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

	@GetMapping("/updateEpisode")
	public String updateEpisodeForm(@RequestParam(name = "pseq", required = true) int pseq,
			@RequestParam(name = "episode_num", required = true) String episode_num, Model model, Episode episode) {
		try {
			// episode_num을 사용하여 에피소드 정보를 가져옴
			Optional<Episode> episodeOptional = episodeService.getEpisodeByPseqAndEpisodeNum(pseq, episode_num);

			// 해당 에피소드 정보가 존재하면 모델에 추가하여 Thymeleaf에서 사용할 수 있도록 함
			if (episodeOptional.isPresent()) {
				Episode existingEpisode = episodeOptional.get();
				model.addAttribute("episode", existingEpisode);
				model.addAttribute("pseq", pseq);
				return "layout/admin/updateEpisode";
			} else {
				// 에피소드 정보가 존재하지 않으면 에러 페이지로 이동
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@PostMapping("/updateEpisode")
	public String updateEpisode(@ModelAttribute("episode") Episode episode, @RequestParam("pseq") int pseq) {
		try {
			// pseq를 사용하여 TVProgram 객체를 가져옴
			TVProgram tvProgram = tvProgramService.getTVProgramByPseq(pseq);

			// 해당 에피소드가 실제로 존재하는지 확인
			Optional<Episode> existingEpisodeOptional = episodeService.getEpisodeByPseqAndEpisodeNum(pseq,
					episode.getEpisode_num());

			if (existingEpisodeOptional.isPresent()) {
				Episode existingEpisode = existingEpisodeOptional.get();

				// episode 객체에 TVProgram 설정
				episode.setTvProgram(tvProgram);

				// episode 객체를 이용하여 에피소드 정보 업데이트 로직 수행
				episodeService.updateEpisode(episode);

				// pseq 값을 가져와서 리다이렉트 URL에 포함시킴
				pseq = existingEpisode.getTvProgram().getPseq();

				// episode_num 값을 가져와서 리다이렉트 URL에 포함시킴
				String episodeNum = existingEpisode.getEpisode_num();

				return "redirect:/getTVProgram?pseq=" + pseq;
			} else {
				// 에피소드 정보가 존재하지 않으면 에러 페이지로 이동
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@GetMapping("/getEpisode")
	public String getEpisode(Model model, @RequestParam("episode_num") String episode_num) {
		// 새로운 Episode 객체를 생성하고 Episode_num를 설정
		Episode episode = new Episode();

		episode.setEpisode_num(episode_num);

		// episodeService.getEpisode에 올바른 episode 객체를 전달
		episode = episodeService.getEpisode(episode);

		model.addAttribute("episode", episode);

		return "layout/admin/getEpisode";
	}
	@GetMapping("/deleteEpisode/{episode_num}")
	public String deleteEpisode(@PathVariable("episode_num")String episode_num, @RequestParam("pseq") int pseq) {
		episodeService.deleteEpisodeByEpisodeNum(episode_num);
		
		return "redirect:/getTVProgram?pseq=" + pseq;
	}

}
