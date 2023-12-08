package com.ott.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ott.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {

	  @Query("SELECT m FROM Movie m WHERE m.keyword LIKE %:keyword%")
	   List<Movie> findByKeywordContaining(@Param("keyword") String genre);
	  
		@Query("SELECT m FROM Movie m WHERE m.m_title_ko LIKE %:keyword% OR m.genre LIKE %:keyword% OR m.cast LIKE %:keyword% ")
		 List<Movie> movieByKeyword(@Param("keyword") String keyword);

}
