package com.ott.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

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
public class Movie {
	@Id
	private String movie_code; // 영화 일련번호(국내:k, 해외:f)
	private String mTitle_ko; // 영화제목 국문
	private String mTitle_ori; // 영화제목 원제
	private String category; // 카테고리
	private String genre; // 장르
	private String nation; // 제작 국가
	private String prodYear; // 제작 년도
	private String director; // 감독
	private String cast; // 출연진
	private String rating; // 관람등급
	private String running_time; // 러닝타임
	private String keyword; // 영화 내용 키워드
	private String description; // 줄거리, 상세설명
	private String image_path; // 썸네일 이미지 경로
	//1
}
