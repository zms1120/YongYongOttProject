package com.ott.episode;

import java.util.List;
import java.util.Optional;

import com.ott.entity.Episode;

public interface EpisodeService {

	List<Episode> getEpList(int pseq);
	
	void insertEpisode(Episode episode);
	
	void updateEpisode(Episode episode);
	
	 Optional<Episode> getEpisodeByPseqAndEpisodeNum(int pseq, String episode_num);
	 
	 Episode getEpisode(Episode episode);
	
	

}