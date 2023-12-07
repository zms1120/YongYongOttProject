package com.ott.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ott.entity.BroadcastEpisode;

public interface BroadcastEpisodeRepository extends JpaRepository<BroadcastEpisode, String> {

	@Query("SELECT b FROM BroadcastEpisode b JOIN b.tvProgram t WHERE t.pseq = :pseq")
	List<BroadcastEpisode> getEpList(int pseq);


}
