package com.ott.board;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.ott.entity.Board;
import com.ott.entity.Member;

public interface BoardService {
	
	Board getBoard(Board board);
	
	List<Board> getBoardList(Board board);
	 
	List<Board> findByMemberIdAndBCategory(String memberId,String bCategory);
	
	List<Board> getBoardByBCategory(String bCategory);
	
	 List<Board> findAllOrderByCntDesc(Board board);
	 
	List<Board> findByIdBoardList(String memberId);
	
	void insertBoard(Board board);
	
	void updateBoard(Board board);
	
	void deleteBoard(int b_seq);
	
	void increaseViewCount(int boardSeq);
	
	void increaseLikeCount(int bSeq);
	
	List<Board> getBoardListByQna(Board board);
}

