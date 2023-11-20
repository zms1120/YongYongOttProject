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
	private String pseq;			//���α׷��Ϸù�ȣ
	private String pTitle;			//���α׷�����
	private String episodeNum;   	//ȸ��
	private String pBoardCasting;   //��ۻ�
	private String airingPeriod;	//�濵�Ⱓ
	private String pAirDate;		//ȸ�����濵��
	private String pDirector;		//����
	private String pWriter;			//�۰�
	private String pCast;			//�⿬��
	private String genre;			//�帣
	private String nation;			//����
	private String rating;			//���
	private String description;		//�ٰŸ�
	private String image_path;		//�����
	
	
}