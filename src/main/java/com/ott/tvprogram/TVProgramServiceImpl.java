package com.ott.tvprogram;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ott.entity.TVProgram;
import com.ott.repository.TVProgramRepository;

@Service
public class TVProgramServiceImpl implements TVProgramService {

	@Autowired
	private TVProgramRepository tvProgramRepository;
	@Value("${upload.directory}")
	private String uploadDirectory;

	@Override
	public List<TVProgram> getTVProgramList(TVProgram tvProgram) {

		return tvProgramRepository.findAll();
	}

	@Override
	public TVProgram getTVProgram(TVProgram tvProgram) {
		// TODO Auto-generated method stub
		return tvProgramRepository.findById(tvProgram.getPseq()).get();
	}

	@Override
	public void deleteTVProgram(TVProgram tvProgram) {
		tvProgramRepository.deleteById(tvProgram.getPseq());

	}
	@Transactional
	@Override
	public void insertTVProgram(TVProgram tvProgram, MultipartFile imageFile, MultipartFile bannerFile) {
		try {
            // Movie 저장
            tvProgramRepository.save(tvProgram);

            // 저장된 Movie의 movie_code를 가져옴
            int pseq = tvProgram.getPseq();

            // 이미지 파일명을 그대로 사용
            String imageFileName = imageFile.getOriginalFilename();
            // movie_code를 파일 이름으로 사용하여 이미지 저장
            String imagePath = saveFile(imageFile, imageFileName);

            // 이미지 경로를 Movie에 저장
            TVProgram savedTV = tvProgramRepository.findById(pseq).orElse(null);
            if (savedTV != null) {
            	savedTV.setImage_path(imagePath);
                tvProgramRepository.save(savedTV);
            }

            // 배너 파일명 그대로 사용
            String bannerFileName = bannerFile.getOriginalFilename();
            // movie_code를 파일 이름으로 사용하여 배너 이미지 저장
            String bannerPath = saveFile(bannerFile, bannerFileName);

            // 배너 이미지 경로를 Movie에 저장
            if (savedTV != null) {
            	savedTV.setBanner_path(bannerPath);
                tvProgramRepository.save(savedTV);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("이미지 저장 중 오류 발생");
        }
    }

	  private String saveFile(MultipartFile file, String fileName) throws IOException {
	    	String filePath = uploadDirectory + "assets/images/tv" + File.separator + fileName;
	        try (OutputStream os = new FileOutputStream(new File(filePath))) {
	            os.write(file.getBytes());
	        }
	        return fileName;
	    }
	@Override
	public void updateTVProgram(TVProgram tvProgram, MultipartFile imageFile, MultipartFile bannerFile) {
		try {
	        // 이미지 파일명을 그대로 사용
	        String imageFileName = imageFile.getOriginalFilename();
	        
	        // 이미지 파일이 비어 있는지 확인
	        if (!imageFile.isEmpty()) {
	            // movie_code를 파일 이름으로 사용하여 이미지 저장
	            String imagePath = saveFile(imageFile, imageFileName);
	            System.out.println("파일이 저장될 위치: " + imagePath);
	            
	            // 이미지 경로를 Movie에 저장
	             tvProgram.setImage_path(imagePath);
	        }

	        // 배너 파일명을 그대로 사용
	        String bannerFileName = bannerFile.getOriginalFilename();
	        
	        // 배너 파일이 비어 있는지 확인
	        if (!bannerFile.isEmpty()) {
	            // movie_code를 파일 이름으로 사용하여 배너 이미지 저장
	            String bannerPath = saveFile(bannerFile, bannerFileName);
	            
	            // 배너 이미지 경로를 Movie에 저장
	            tvProgram.setBanner_path(bannerPath);
	        }

	        // Movie 저장 (이미 저장된 엔터티를 다시 저장할 필요 없음)
	        tvProgramRepository.save(tvProgram);

	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new RuntimeException("이미지 저장 중 오류 발생");
	    }
	}
	@Override
	public String getVideoPath(int pseq) {

		return "/video/" + pseq + ".mp4";
	}

	@Override
	public List<TVProgram> findByCategoryContaining(String category) {
		
		return tvProgramRepository.findByCategoryContaining(category);
	}

	@Override
	public TVProgram getTVProgramByPseq(int pseq) {
		
		 return tvProgramRepository.findById(pseq).orElse(null);
	}

}
