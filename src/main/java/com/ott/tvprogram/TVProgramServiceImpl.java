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
            TVProgram savedTVProgram = tvProgramRepository.findById(pseq).orElse(null);
            if (savedTVProgram != null) {
            	savedTVProgram.setImage_path(imagePath);
                tvProgramRepository.save(savedTVProgram);
            }

            // 배너 파일명 그대로 사용
            String bannerFileName = bannerFile.getOriginalFilename();
            // movie_code를 파일 이름으로 사용하여 배너 이미지 저장
            String bannerPath = saveFile(bannerFile, bannerFileName);

            // 배너 이미지 경로를 Movie에 저장
            if (savedTVProgram != null) {
            	savedTVProgram.setBanner_path(bannerPath);
                tvProgramRepository.save(savedTVProgram);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("이미지 저장 중 오류 발생");
        }
    }

    private String saveFile(MultipartFile file, String fileName) throws IOException {
        String filePath = uploadDirectory + File.separator + fileName;
        try (OutputStream os = new FileOutputStream(new File(filePath))) {
            os.write(file.getBytes());
        }
        return fileName;
	    }
	@Override
	public void updateTVProgram(TVProgram tvProgram, MultipartFile imageFile) {
		try {
			// TV 프로그램을 저장
			tvProgramRepository.save(tvProgram);

			// 저장된 TV 프로그램의 pseq를 가져옴
			int pseq = tvProgram.getPseq();

			// pseq를 파일 이름으로 사용하여 이미지 저장
			String filePath = uploadDirectory + File.separator + pseq + ".jpg";
			imageFile.transferTo(new File(filePath));

			// 이미지 경로를 TV 프로그램에 저장
			TVProgram savedTVProgram = tvProgramRepository.findById(pseq).orElse(null);
			if (savedTVProgram != null) {
				savedTVProgram.setImage_path(filePath);
				tvProgramRepository.save(savedTVProgram);
			}
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

}
