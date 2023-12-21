package com.ott.episode;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ott.entity.Episode;
import com.ott.repository.EpisodeRepository;
@Service
public class EpisodeServiceImpl implements EpisodeService {

	@Autowired
	private EpisodeRepository episodeRepository;
	
	@Override
	public List<Episode> getEpList(int pseq){
		return episodeRepository.getEpList(pseq);
	}

	@Override
	public void insertEpisode(Episode episode) {
		
		episodeRepository.save(episode);
	}

	@Override
	public void updateEpisode(Episode episode) {
		episodeRepository.save(episode);
		
	}

	  @Override
	    public Optional<Episode> getEpisodeByPseqAndEpisodeNum(int pseq, String episode_num) {
	        return episodeRepository.findByPseqAndEpisodeNum(pseq, episode_num);
	
	  }

	@Override
	public Episode getEpisode(Episode episode) {
		// TODO Auto-generated method stub
		return episodeRepository.findById(episode.getEpisode_num()).get();
	}

	@Override
	public void deleteEpisodeByEpisodeNum(String episode_num) {
		episodeRepository.deleteById(episode_num);
		
	}
}
