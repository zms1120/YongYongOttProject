package com.ott.entity;

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
	private int r_seq;										//��� ��ȣ
	private String content;									//��� ����
	private int r_like;										//��� ��õ��
	private String report;									//��� �Ű�
	
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "b_seq")  // "b_seq" �÷��� �ܷ�Ű�� ���
    private Board board;  // Board ��ƼƼ���� ����				//�Խñ� ��ȣ ��ȸ
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")		
	private Member member;									//��� �ۼ���
    
}
