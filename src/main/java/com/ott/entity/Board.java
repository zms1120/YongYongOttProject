package com.ott.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Board {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_generator")
	@SequenceGenerator(name = "board_seq_generator", sequenceName = "board_seq", allocationSize = 1)
	@Id
	private int b_seq;						//�Խñ� �Ϸù�ȣ
	private String title;					//�Խñ� ����
	private String content;					//�Խñ� ����
	private int cnt;						//�Խñ� ��ȸ��
	private String b_category;				//�Խñ� �׸�
	private int b_like;						//�Խñ� ��õ��
	private String report;					// �Խñ� �Ű�
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")		
	private Member member;					// �Խñ� �ۼ���
	

	@OneToMany(mappedBy = "board")
	private List<Reply> replies = new ArrayList<>();
	
}
