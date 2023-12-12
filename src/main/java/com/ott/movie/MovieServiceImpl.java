package com.ott.movie;



import com.ott.entity.Movie;
import com.ott.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;



@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Value("${upload.directory}")
	private String uploadDirectory;


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
	public void updateMovie(Movie movie, MultipartFile imageFile) {
		movieRepository.save(movie);
		
	}


	@Override
	public void insertMovie(Movie movie, MultipartFile imageFile) {
		try {
			// TV 프로그램을 저장
			movieRepository.save(movie);

			// 저장된 TV 프로그램의 pseq를 가져옴
			String movie_code = movie.getMovie_code();

			// pseq를 파일 이름으로 사용하여 이미지 저장
			String filePath = uploadDirectory + File.separator + movie_code + ".jpg";
			imageFile.transferTo(new File(filePath));

			// 이미지 경로를 TV 프로그램에 저장
			Movie saveMovie = movieRepository.findById(movie_code).orElse(null);
			if (saveMovie != null) {
				saveMovie.setImage_path(filePath);
				movieRepository.save(saveMovie);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("이미지 저장 중 오류 발생");
		}
		
	}


	@Override
	public List<Movie> findByKeywordContaining(String keyword) {
		
		return movieRepository.findByKeywordContaining(keyword); 
	}
}
