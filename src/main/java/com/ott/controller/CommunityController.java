package com.ott.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ott.board.BoardService;
import com.ott.entity.Board;
import com.ott.entity.Member;
import com.ott.entity.Reply;
import com.ott.reply.ReplyService;

@Controller
public class CommunityController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;
	

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
 
	// 게시판 상세 및 댓글까지 보이기(댓글은 로그인시에만)
	   @GetMapping("/gopost")
	   public String getBoard(Board board, Model model, Reply reply, @RequestParam(name = "b_seq")int boardSeq) {
		  Board selectedBoard = boardService.getBoard(board);
		  // 게시글 조회수 증가
		  boardService.increaseViewCount(boardSeq);
	      model.addAttribute("board", selectedBoard);
	      model.addAttribute("reply", replyService.getReply(boardSeq));
	      
	      return "layout/community/getBoard";
	   }

	
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "layout/community/insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(@RequestParam(name = "id") String id,Board board, Model model, Member member) {
	      member.setId(id);
	      board.setMember(member);
	      model.addAttribute("id", id);

	      boardService.insertBoard(board);
	       return "redirect:/community";

	}
	@GetMapping("updateBoard")
	public String updateBoardView() {
		return "layout/community/updateBoard";
		
	}
	@PostMapping("updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		
		return "redirect:/community";
	}
	
	@PostMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		
		return "layout/community/community";
	}
	
	// 댓글 등록하기
	@PostMapping("/insertReply")
	public String replyAction(@RequestParam(name = "id") String id, @ModelAttribute("newReply") Reply reply,
	        Model model, Member member, Board board) {

	    member.setId(id);
	    reply.setMember(member);
	    reply.setBoard(board);
	    model.addAttribute("id", id);
	    model.addAttribute("b_seq", board.getB_seq());

	    replyService.insertReply(reply);

	    // 댓글이 등록된 후 해당 게시글의 상세 페이지로 이동
	    return "redirect:/gopost?b_seq=" + board.getB_seq();
	}
	
	@PostMapping("/like")
	   public ResponseEntity<String> likeAction(@RequestBody Map<String, Object> likeCount) {
	      int b_seq = Integer.parseInt(likeCount.get("b_seq").toString());
	       System.out.println("번호: " + b_seq);
	       boardService.increaseLikeCount(b_seq);
	       return ResponseEntity.ok("Liked successfully");
	   }

}
