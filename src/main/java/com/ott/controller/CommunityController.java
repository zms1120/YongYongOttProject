package com.ott.controller;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ott.board.BoardService;
import com.ott.entity.Board;
import com.ott.entity.Member;
import com.ott.entity.Reply;
import com.ott.reply.ReplyService;
import com.ott.security.SecurityUser;

@Controller
public class CommunityController {

	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;

	@GetMapping("/community")
	public String getBoardListByCategory(@RequestParam(name = "category", required = false) String category,
			Model model, Board board) {
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
		// 인기글 목록을 가져오는 로직
		System.out.println("hot:" + hotBoardList);
		model.addAttribute("hotBoardList", hotBoardList);
		return "layout/community/community";
	}

	// 게시판 상세 및 댓글까지 보이기(댓글은 로그인시에만)
	@GetMapping("/gopost")
	public String getBoard(Board board, Model model, Reply reply, @RequestParam(name = "b_seq") int boardSeq) {
		Board selectedBoard = boardService.getBoard(board);
		// 게시글 조회수 증가
		boardService.increaseViewCount(boardSeq);
		model.addAttribute("board", selectedBoard);
		model.addAttribute("reply", replyService.getReply(boardSeq));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// 사용자가 인증되어 있지 않다면, 로그인하지 않은 상태로 이용권 페이지 열기
		if (authentication == null || !authentication.isAuthenticated()) {
			return "layout/member/getBoard";
		}

		// 사용자가 인증되어 있다면, 사용자 정보를 가져와서 모델에 추가
		Object principal = authentication.getPrincipal();
		if (principal instanceof SecurityUser) {
			SecurityUser securityUser = (SecurityUser) principal;
			Member member = securityUser.getMember();
			model.addAttribute("member", member);
		}
		return "layout/community/getBoard";
	}

	@GetMapping("/insertBoard")
	public String insertBoardView(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
		Member member = securityUser.getMember();
		model.addAttribute("member", member);
		return "layout/community/insertBoard";
	}

	@PostMapping("/insertBoard")
	public String insertBoard(@RequestParam(name = "id") String id, Board board, Model model, Member member) {
		member.setId(id);
		board.setMember(member);
		model.addAttribute("id", id);

		boardService.insertBoard(board);
		return "redirect:/community";

	}

	@GetMapping("updateBoard")
	public String updateBoardView(Board board, Model model, Reply reply, @RequestParam(name = "b_seq") int boardSeq,  @AuthenticationPrincipal SecurityUser securityUser) {

		Board selectedBoard = boardService.getBoard(board);
		model.addAttribute("board", selectedBoard);
		Member member = securityUser.getMember();
		model.addAttribute("member", member);
		return "layout/community/updateBoard";

	}
	@Transactional
	@PostMapping("updateBoard")
	public String updateBoard(Board board) {

		boardService.updateBoard(board);
		return "redirect:/community";
	}
	
	//게시글 삭제
	@GetMapping("/deleteBoard/{b_seq}")
	public String deleteBoard(@PathVariable("b_seq") int b_seq) {
		boardService.deleteBoard(b_seq);
		System.out.println(b_seq);

			
			return "redirect:/community";
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

		//댓글 삭제
		@GetMapping("/deleteReply/{r_seq}")
		public String deleteReply(@PathVariable("r_seq") int r_seq, @RequestParam("b_seq") int b_seq) {
			replyService.deleteReply(r_seq);

			return "redirect:/gopost?b_seq=" + b_seq;
		}

		@PostMapping("/like")
		   public ResponseEntity<String> likeAction(@RequestBody Map<String, Object> likeCount) {
		      int b_seq = Integer.parseInt(likeCount.get("b_seq").toString());
		       System.out.println("번호: " + b_seq);
		       boardService.increaseLikeCount(b_seq);
		       return ResponseEntity.ok("Liked successfully");
		   }

		@GetMapping("/faq")
		   public String faq() {
		      
		      return "layout/community/faq";
		   }
}
