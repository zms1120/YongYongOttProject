package com.ott.episode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ott.entity.BroadcastEpisode;
import com.ott.repository.BroadcastEpisodeRepository;
@Service
public class BroadcastEpisodeServiceImpl implements BroadcastEpisodeService {

	@Autowired
	private BroadcastEpisodeRepository broadcastEpisodeRepository;
	
	@Override
	public List<BroadcastEpisode> getEpList(int pseq){
		return broadcastEpisodeRepository.getEpList(pseq);
	}
}
