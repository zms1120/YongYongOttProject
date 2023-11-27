package com.ott.entity;

import java.util.ArrayList;
import java.util.List;

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
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TVProgram {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tvprog_seq_generator")
	@SequenceGenerator(name = "tvprog_seq_generator", sequenceName = "tvprog_seq", allocationSize = 1)
	private int pseq;				//���α׷��Ϸù�ȣ
	private String pTitle;			//���α׷�����
	private String pBoardCasting;   //��ۻ�
	private String airingPeriod;	//�濵�Ⱓ
	private String pDirector;		//����
	private String pWriter;			//�۰�
	private String pCast;			//�⿬��
	private	String pCategory;		//���α׷�ī�װ�
	private String nation;			//����
	private String rating;			//���
	private String description;		//�ٰŸ�
	private String image_path;		//�����
	

	@OneToMany(mappedBy = "tvProgram")
	private List<BroadcastEpisode> broadcastEpisodes = new ArrayList<>();
	
	
}