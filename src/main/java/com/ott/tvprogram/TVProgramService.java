package com.ott.tvprogram;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ott.entity.TVProgram;

public interface TVProgramService {

    List<TVProgram> getTVProgramList(TVProgram tvProgram);
    
    TVProgram getTVProgram(TVProgram tvProgram);
    
    void insertTVProgram(TVProgram tvProgram,  MultipartFile imageFile, MultipartFile bannerFile);
    
    void updateTVProgram(TVProgram tvProgram,  MultipartFile imageFile, MultipartFile bannerFile);
    
    void deleteTVProgramByPseq(int pseq);
    
    String getVideoPath(int pseq);
   
    List<TVProgram> findByCategoryContaining(String category);
    
    TVProgram getTVProgramByPseq(int pseq);
    
   
}