package com.ott.episode;

import java.util.List;

import com.ott.entity.BroadcastEpisode;

public interface BroadcastEpisodeService {

	List<BroadcastEpisode> getEpList(int pseq);

}