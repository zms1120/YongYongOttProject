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
    @JoinColumn(name = "b_seq")  // "b_seq" �÷��� �ܷ�Ű�� ���
    private Board board;  // Board ��ƼƼ���� ����

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")  // "id" �÷��� �ܷ�Ű�� ���
    private Member member;  // Member ��ƼƼ���� ����
}
