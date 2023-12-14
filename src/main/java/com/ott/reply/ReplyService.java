package com.ott.reply;

import java.util.List;
import java.util.Optional;

import com.ott.entity.Reply;

public interface ReplyService {

	// 댓글 등록
	void insertReply(Reply reply);

	// 댓글 수정
	void updateReply(Reply reply);

	// 댓글 삭제
	void deleteReply(Reply reply);
	
	// 댓글 보기
	List<Reply> getReply(int boardSeq);

}