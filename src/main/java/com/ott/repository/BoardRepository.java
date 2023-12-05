package com.ott.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ott.entity.Board;
import com.ott.entity.Movie;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	
	@Query("SELECT b FROM Board b WHERE b.member.id = :id AND b.b_category = :b_category")
	List<Board> findByMemberIdAndBCategory(@Param("id") String memberId, @Param("b_category") String bCategory);


	

	
	

	


}
