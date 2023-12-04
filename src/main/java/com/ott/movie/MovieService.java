package com.ott.movie;


import com.ott.entity.Movie;

import java.util.List;

import org.springframework.data.repository.query.Param;

public interface MovieService {
    List<Movie> getMovieList(Movie movie);
    
    Movie getMovie(Movie movie);
    
    void insertMovie(Movie movie);
    
    void deleteMovie(Movie movie);
    
    void updateMovie(Movie movie);
    
    String getVideoPath(String movieCode);
    
    List<Movie> findByKeywordContaining(String keyword);
}

