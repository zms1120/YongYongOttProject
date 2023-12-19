package com.ott.movie;



import com.ott.entity.Movie;
import com.ott.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;




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


	
	@Transactional
	@Override
	public void updateMovie(Movie movie, MultipartFile imageFile, MultipartFile bannerFile) {
	    try {
	        // 이미지 파일명을 그대로 사용
	        String imageFileName = imageFile.getOriginalFilename();
	        
	        // 이미지 파일이 비어 있는지 확인
	        if (!imageFile.isEmpty()) {
	            // movie_code를 파일 이름으로 사용하여 이미지 저장
	            String imagePath = saveFile(imageFile, imageFileName);
	            System.out.println("파일이 저장될 위치: " + imagePath);
	            
	            // 이미지 경로를 Movie에 저장
	            movie.setImage_path(imagePath);
	        }

	        // 배너 파일명을 그대로 사용
	        String bannerFileName = bannerFile.getOriginalFilename();
	        
	        // 배너 파일이 비어 있는지 확인
	        if (!bannerFile.isEmpty()) {
	            // movie_code를 파일 이름으로 사용하여 배너 이미지 저장
	            String bannerPath = saveFile(bannerFile, bannerFileName);
	            
	            // 배너 이미지 경로를 Movie에 저장
	            movie.setBanner_path(bannerPath);
	        }

	        // Movie 저장 (이미 저장된 엔터티를 다시 저장할 필요 없음)
	        movieRepository.save(movie);

	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new RuntimeException("이미지 저장 중 오류 발생");
	    }
	}





	

    @Transactional
    @Override
    public void insertMovie(Movie movie, MultipartFile imageFile, MultipartFile bannerFile) {
        try {
            // Movie 저장
            movieRepository.save(movie);

            // 저장된 Movie의 movie_code를 가져옴
            String movieCode = movie.getMovie_code();

            // 이미지 파일명을 그대로 사용
            String imageFileName = imageFile.getOriginalFilename();
            // movie_code를 파일 이름으로 사용하여 이미지 저장
            String imagePath = saveFile(imageFile, imageFileName);

            // 이미지 경로를 Movie에 저장
            Movie savedMovie = movieRepository.findById(movieCode).orElse(null);
            if (savedMovie != null) {
                savedMovie.setImage_path(imagePath);
                movieRepository.save(savedMovie);
            }

            // 배너 파일명 그대로 사용
            String bannerFileName = bannerFile.getOriginalFilename();
            // movie_code를 파일 이름으로 사용하여 배너 이미지 저장
            String bannerPath = saveFile(bannerFile, bannerFileName);

            // 배너 이미지 경로를 Movie에 저장
            if (savedMovie != null) {
                savedMovie.setBanner_path(bannerPath);
                movieRepository.save(savedMovie);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("이미지 저장 중 오류 발생");
        }
    }
    private String saveFile(MultipartFile file, String fileName) throws IOException {
    	String filePath = uploadDirectory + "assets/images/movie" + File.separator + fileName;
        try (OutputStream os = new FileOutputStream(new File(filePath))) {
            os.write(file.getBytes());
        }
        return fileName;
    }


	@Override
	public List<Movie> findByKeywordContaining(String keyword) {
		
		return movieRepository.findByKeywordContaining(keyword); 
	}


	@Override
	public Movie getMovieByCode(String movieCode) {
		  // movieRepository를 사용하여 영화 코드에 해당하는 영화 정보를 가져옴
		 return movieRepository.findById(movieCode).orElse(null);
	}
}
