package com.ott.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ott.entity.Movie;
import com.ott.entity.TVProgram;
import com.ott.repository.MovieRepository;
import com.ott.repository.TVProgramRepository;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private TVProgramRepository tvProgramRepository;

	@Override
	public List<Movie> movieByKeyword(String keyword) {
		
		return movieRepository.movieByKeyword(keyword);
	}

	@Override
	public List<TVProgram> tvProgramByKeyword(String keyword) {
		
		return tvProgramRepository.tvProgramByKeyword(keyword);
	}
	
	

}
