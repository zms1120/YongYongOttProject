package com.ott.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
	private String content;					//게시글 내용
	private int cnt;						//게시글 조회수
	private String b_category;				//게시글 항목
	private int b_like;						//게시글 추천수
	private String report;					// 게시글 신고
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")		
	private Member member;					// 게시글 작성자
	

	@OneToMany(mappedBy = "board")
	private List<Reply> replies = new ArrayList<>();
	
}
