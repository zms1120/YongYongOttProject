package com.ott.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.ott.entity.TVProgram;

public interface TVProgramRepository extends JpaRepository<TVProgram, Integer> {

	@Query("SELECT t FROM TVProgram t WHERE t.p_category LIKE %:p_category%")
	List<TVProgram> findByCategoryContaining(@Param("p_category") String category);
	
	 @Query("SELECT t FROM TVProgram t WHERE t.p_title LIKE %:keyword% OR t.p_category LIKE %:keyword% OR t.p_cast LIKE %:keyword% ")
	 List<TVProgram> tvProgramByKeyword(@Param("keyword") String keyword);

}
