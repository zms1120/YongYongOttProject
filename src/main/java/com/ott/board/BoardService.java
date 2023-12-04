package com.ott.board;

import java.util.List;


import com.ott.entity.Board;
import com.ott.entity.Member;

public interface BoardService {
	
	Board getBoard(Board board);
	
	 List<Board> getBoardList(Board board);
	 
	List<Board> findByMemberIdAndBCategory(String memberId,String bCategory);
	
	List<Board> findByMemberIdAndAllBCategory(String memberId);

}