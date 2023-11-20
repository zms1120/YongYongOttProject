package com.ott.entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

public class Reply {


	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rep_seq_generator")
	@SequenceGenerator(name = "rep_seq_generator", sequenceName = "rep_seq", allocationSize = 1)
	@Id
	private String r_seq;									//��� ��ȣ
	private String content;									//��� ����
	private int like;										//��� ��õ��
	private String report;									//��� �Ű�
	
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "b_seq")  // "b_seq" �÷��� �ܷ�Ű�� ���
    private Board board;  // Board ��ƼƼ���� ����				//�Խñ� ��ȣ ��ȸ

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")  // "id" �÷��� �ܷ�Ű�� ���
    private Member writer;  // Member ��ƼƼ���� ���� 			//��� �ۼ���
    //1
}
