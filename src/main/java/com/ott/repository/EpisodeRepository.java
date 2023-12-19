package com.ott.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.ott.entity.Episode;

public interface EpisodeRepository extends JpaRepository<Episode, String> {

	@Query("SELECT b FROM Episode b JOIN b.tvProgram t WHERE t.pseq = :pseq")
	List<Episode> getEpList(int pseq);

	@Query("SELECT e FROM Episode e JOIN e.tvProgram t WHERE t.pseq = :pseq AND e.episode_num = :episode_num")
	Optional<Episode> findByPseqAndEpisodeNum(int pseq, String episode_num);



}
