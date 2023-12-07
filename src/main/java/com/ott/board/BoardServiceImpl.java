package com.ott.board;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ott.entity.Board;
import com.ott.entity.Member;
import com.ott.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	 @Override
	public List<Board> findByMemberIdAndBCategory(String memberId,String bCategory) {
	        return boardRepository.findByMemberIdAndBCategory(memberId,bCategory);
	    }

	@Override
	public Board getBoard(Board board) {
		
		return boardRepository.findById(board.getB_seq()).get();
	}

	@Override
	public List<Board> getBoardList(Board board) {
		
		return boardRepository.findAll();
	}



	
}
