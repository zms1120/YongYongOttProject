package com.ott.episode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ott.entity.Episode;
import com.ott.repository.EpisodeRepository;
@Service
public class EpisodeServiceImpl implements EpisodeService {

	@Autowired
	private EpisodeRepository broadcastEpisodeRepository;
	
	@Override
	public List<Episode> getEpList(int pseq){
		return broadcastEpisodeRepository.getEpList(pseq);
	}
}
