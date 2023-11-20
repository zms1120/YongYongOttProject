package com.ott.entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rep_seq_generator")
	@SequenceGenerator(name = "rep_seq_generator", sequenceName = "rep_seq", allocationSize = 1)
	private String r_seq;
	private String content;
	private int like;
	private String report;
	
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "b_seq")  // "b_seq" 컬럼을 외래키로 사용
    private Board board;  // Board 엔티티와의 매핑

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")  // "id" 컬럼을 외래키로 사용
    private Member member;  // Member 엔티티와의 매핑
}
