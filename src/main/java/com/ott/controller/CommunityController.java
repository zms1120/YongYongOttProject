package com.ott.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ott.board.BoardService;
import com.ott.entity.Board;
import com.ott.entity.Member;

@Controller
public class CommunityController {

	@Autowired
	private BoardService boardService;
	

	@GetMapping("/community")
	public String getBoardListByCategory(@RequestParam(name = "category", required = false) String category, Model model, Board board) {
	    System.out.println("Category: " + category);

	    // 카테고리가 제공되지 않으면 전체 목록을 가져오는 로직
	    if (category == null) {
	        List<Board> allBoardList = boardService.getBoardList(board);
	        model.addAttribute("boardList", allBoardList);
	        
	    } else {
	        // 특정 카테고리에 해당하는 목록을 가져오는 로직
	        List<Board> boardList = boardService.getBoardByBCategory(category);
	        model.addAttribute("boardList", boardList);
	    }
	    List<Board> hotBoardList = boardService.findAllOrderByCntDesc(board);
	    //인기글 목록을 가져오는 로직
	    System.out.println("hot:" + hotBoardList );
	    model.addAttribute("hotBoardList", hotBoardList);
	    return "layout/community/community";
	}
 
}
