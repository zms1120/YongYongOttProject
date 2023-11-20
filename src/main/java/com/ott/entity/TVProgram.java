package com.ott.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class TVProgram {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tvprog_seq_generator")
	@SequenceGenerator(name = "tvprog_seq_generator", sequenceName = "tvprog_seq", allocationSize = 1)
	private String pseq;			//프로그램일련번호
	private String pTitle;			//프로그램제목
	private String episodeNum;   	//회차
	private String pBoardCasting;   //방송사
	private String airingPeriod;	//방영기간
	private String pAirDate;		//회차별방영일
	private String pDirector;		//감독
	private String pWriter;			//작가
	private String pCast;			//출연진
	private String genre;			//장르
	private String nation;			//나라
	private String rating;			//등급
	private String description;		//줄거리
	private String image_path;		//썸네일
	
	
}