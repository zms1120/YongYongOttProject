package com.ott.movie;



import com.ott.entity.Movie;
import com.ott.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;


    @Override
	public Movie getMovie(Movie movie) {
		return movieRepository.findById(movie.getMovie_code()).get();
	}

    
    @Override
    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }

	@Override
	public List<Movie> getMovieList(Movie movie) {
		
		return (List<Movie>) movieRepository.findAll();
	}

	@Override
	public String getVideoPath(String movieCode) {
		// TODO Auto-generated method stub
		return "/video/" + movieCode + ".mp4";
	}


	

	@Override
	public void updateMovie(Movie movie) {
		movieRepository.save(movie);
		
	}


	@Override
	public void insertMovie(Movie movie) {
		movieRepository.save(movie);
		
	}


	@Override
	public List<Movie> findByKeywordContaining(String keyword) {
		
		return movieRepository.findByKeywordContaining(keyword); 
	}
}
