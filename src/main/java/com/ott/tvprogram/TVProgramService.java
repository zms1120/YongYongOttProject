package com.ott.tvprogram;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ott.entity.TVProgram;

public interface TVProgramService {

    List<TVProgram> getTVProgramList(TVProgram tvProgram);
    
    TVProgram getTVProgram(TVProgram tvProgram);
    
    void insertTVProgram(TVProgram tvProgram, MultipartFile imageFile);
    
    void updateTVProgram(TVProgram tvProgram,  MultipartFile imageFile);
    
    void deleteTVProgram(TVProgram tvProgram);
    
    String getVideoPath(int pseq);
   
    List<TVProgram> findByCategoryContaining(String category);
}