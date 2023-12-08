package com.ott.search;

import java.util.List;

import com.ott.entity.Movie;
import com.ott.entity.TVProgram;

public interface SearchService {

	 List<Movie> movieByKeyword(String keyword);
	 
	 List<TVProgram> tvProgramByKeyword(String keyword);
}