package com.ott.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"replies","member"})
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_generator")
	@SequenceGenerator(name = "board_seq_generator", sequenceName = "board_seq", allocationSize = 1)
	@Id
	private int b_seq;						//게시글 일련번호
	private String title;					//게시글 제목
	@Column(length = 4000)
	private String content;					//게시글 내용
	private int cnt = 0;						//게시글 조회수
	private String b_category;				//게시글 항목
	private int b_like;						//게시글 추천수
	private String report;					// 게시글 신고
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date write_date= new Date();   				//게시글 등록일
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")		
	private Member member;					// 게시글 작성자
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<Reply> replies = new ArrayList<>();
	
	 // 댓글 수를 나타내는 필드 추가
    @Transient
    private int replyCount;

    // 댓글 수 계산 메서드
    public int getReplyCount() {
        return replies.size();
    }
	
}
