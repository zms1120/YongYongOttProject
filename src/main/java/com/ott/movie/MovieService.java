package com.ott.movie;


import com.ott.entity.Movie;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

public interface MovieService {
    List<Movie> getMovieList(Movie movie);
    
    Movie getMovie(Movie movie);
    
    void insertMovie(Movie movie, MultipartFile imageFile, MultipartFile bannerFile);
    
    void deleteMovieByMovieCode(String movie_code);
    
    void updateMovie(Movie movie, MultipartFile imageFile, MultipartFile bannerFile);
    
    String getVideoPath(String movieCode);
    
    List<Movie> findByKeywordContaining(String keyword);
    
    Movie getMovieByCode(String movieCode);
}

