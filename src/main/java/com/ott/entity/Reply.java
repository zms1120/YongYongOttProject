package com.ott.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Reply {


	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rep_seq_generator")
	@SequenceGenerator(name = "rep_seq_generator", sequenceName = "rep_seq", allocationSize = 1)
	@Id
	private int r_seq;										//댓글 번호
	@Column(length = 4000)
	private String content;									//댓글 내용
	private int r_like;										//댓글 추천수
	private String report;									//댓글 신고
	
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "b_seq")  // "b_seq" 컬럼을 외래키로 사용
    private Board board;  // Board 엔티티와의 매핑				//게시글 번호 조회
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")		
	private Member member;									//댓글 작성자
    
}
