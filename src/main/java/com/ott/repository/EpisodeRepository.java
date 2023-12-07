package com.ott.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ott.entity.Episode;

public interface EpisodeRepository extends JpaRepository<Episode, String> {

	@Query("SELECT b FROM Episode b JOIN b.tvProgram t WHERE t.pseq = :pseq")
	List<Episode> getEpList(int pseq);


}
