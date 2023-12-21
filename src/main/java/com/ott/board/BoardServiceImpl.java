package com.ott.board;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public List<Board> getBoardByBCategory(String bCategory) {
		
		return boardRepository.getBoardByBCategory(bCategory);
		
		
	}

	@Override
	public List<Board> findAllOrderByCntDesc(Board board) {
		
		return boardRepository.findAllOrderByCntDesc(board);
	}

	@Override
	public List<Board> findByIdBoardList(String memberId) {
	
		return boardRepository.findByIdBoardList(memberId);
	}

	@Override
	public void insertBoard(Board board) {
		boardRepository.save(board);
		
	}

	@Override
	public void updateBoard(Board board) {

		Board updateBoard = boardRepository.findById(board.getB_seq()).get();

		updateBoard.setB_category(board.getB_category());
		updateBoard.setTitle(board.getTitle());
		updateBoard.setContent(board.getContent());
		
		boardRepository.save(updateBoard);
		
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepository.deleteById(board.getB_seq());
		
	}
	
	@Transactional
	@Override
	public void increaseViewCount(int boardSeq) {
		boardRepository.increaseViewCount(boardSeq);
		
	}

	@Transactional
	   public void increaseLikeCount(int bSeq) {
	       boardRepository.increaseLikeCount(bSeq);
	   }


	@Override
	public List<Board> getBoardListByQna(Board board) {
		
		return boardRepository.getBoardListByQna(board);
	}
	



	
}
