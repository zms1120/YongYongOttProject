package com.ott.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ott.entity.Board;
import com.ott.entity.Movie;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	//mypage에서 id를 조건으로 게시글 조회
	@Query("SELECT b FROM Board b WHERE b.member.id = :id AND b.b_category = :b_category")
	List<Board> findByMemberIdAndBCategory(@Param("id") String memberId, @Param("b_category") String bCategory);
	
	//특정 카테고리별 게시글 조회
	@Query("SELECT b FROM Board b WHERE b.b_category = :b_category")
	List<Board> getBoardByBCategory(@Param("b_category")String bCategory);

	//게시글 조회수 많은 순서
	@Query("SELECT b FROM Board b ORDER BY b.cnt DESC")
	List<Board> findAllOrderByCntDesc(Board board);
	

	@Query("SELECT b FROM Board b WHERE b.member.id = :id")
	List<Board> findByIdBoardList(@Param("id")String memberId);
	
	@Modifying
	@Query("UPDATE Board b SET b.cnt = b.cnt + 1 WHERE b.b_seq = :b_seq")
	void increaseViewCount(@Param("b_seq") int boardSeq);
	
	@Modifying
	@Query("UPDATE Board b SET b.b_like = b.b_like + 1 WHERE b.b_seq = :b_seq")
	void increaseLikeCount(@Param("b_seq") int b_seq);


}
