package com.ott.reply;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ott.entity.Reply;
import com.ott.repository.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository replyRepository;

	// 댓글 등록
	@Override
	public void insertReply(Reply reply) {
		replyRepository.save(reply);
	}

	// 댓글 수정
	@Override
	public void updateReply(Reply reply) {
		replyRepository.save(reply);
	}

	// 댓글 삭제
	@Override
	public void deleteReply(int r_seq) {
		replyRepository.deleteById(r_seq);
	}
	
	// 댓글 보기
	@Override
	public List<Reply> getReply(int boardSeq) {
		
		return replyRepository.getReply(boardSeq);
	}

}
