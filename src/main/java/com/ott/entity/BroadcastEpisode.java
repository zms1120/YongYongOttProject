package com.ott.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class BroadcastEpisode {
	
	@Id
	private String episode_num;  //회차 번호
	private String ep_title;		//회차별 제목
	private String running_time; //방영 시간
	private String airing_date;	// 회차별 방영날짜
	private String description; // 회차별 줄거리옌
	private String video_path; // 영상 주소 경로
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pseq")		
	private TVProgram tvProgram;	
	
}
