package com.ott.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ott.board.BoardService;
import com.ott.entity.Board;

@Controller
public class CommunityController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("community")
	public String getBoardList(Board board, Model model) {
		List<Board> BoardList = boardService.getBoardList(board);
		model.addAttribute("boardList", BoardList);
		
		return "layout/community";
		
	}
}
