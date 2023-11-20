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
	private String movie_code; // ��ȭ �Ϸù�ȣ(����:k, �ؿ�:f)
	private String mTitle_ko; // ��ȭ���� ����
	private String mTitle_ori; // ��ȭ���� ����
	private String category; // ī�װ�
	private String genre; // �帣
	private String nation; // ���� ����
	private String openYear; // ���� �⵵
	private String director; // ����
	private String cast; // �⿬��
	private String rating; // �������
	private String running_time; // ����Ÿ��
	private String keyword; // ��ȭ ���� Ű����
	private String description; // �ٰŸ�, �󼼼���
	private String image_path; // ����� �̹��� ���
}
