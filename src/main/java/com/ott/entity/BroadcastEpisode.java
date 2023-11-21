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
	private String episodeNum;  //ȸ�� ��ȣ
	private String epTitle;		//ȸ���� ����
	private String running_time; //�濵 �ð�
	private String airing_date;	// ȸ���� �濵��¥
	private String description; // ȸ���� �ٰŸ���
	private String video_path; // ���� �ּ� ���
	
	 
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pseq")		
	private TVProgram tvProgram;	
	
}
