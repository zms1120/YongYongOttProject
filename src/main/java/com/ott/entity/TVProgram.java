package com.ott.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ToString(exclude = "broadcastEpisodes")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TVProgram {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tvprog_seq_generator")
	@SequenceGenerator(name = "tvprog_seq_generator", sequenceName = "tvprog_seq", allocationSize = 1)
	private int pseq;				//프로그램일련번호
	private String p_title;			//프로그램제목
	private String p_board_casting;   //방송사
	private String airing_period;	//방영기간
	private String p_director;		//감독
	private String p_writer;		//작가
	@Column(length = 4000)
	private String p_cast;			//출연진
	private	String p_category;		//프로그램카테고리
	private String nation;			//나라
	private String rating;			//등급
	@Column(length = 4000)
	private String description;		//줄거리
	private String image_path;		//썸네일
	private String banner_path; //배너 이미지 경로

	@OneToMany(mappedBy = "tvProgram")
	private List<Episode> broadcastEpisodes = new ArrayList<>();
	
	
}