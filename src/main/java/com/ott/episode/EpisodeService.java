package com.ott.episode;

import java.util.List;

import com.ott.entity.Episode;

public interface EpisodeService {

	List<Episode> getEpList(int pseq);

}