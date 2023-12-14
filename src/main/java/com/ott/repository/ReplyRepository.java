package com.ott.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ott.entity.Reply;


public interface ReplyRepository extends JpaRepository<Reply, Integer> {

	@Query("SELECT r FROM Reply r JOIN r.board b WHERE b.b_seq = :b_seq ORDER BY r.r_seq DESC")
	List<Reply> getReply(@Param("b_seq") int boardSeq);

}
