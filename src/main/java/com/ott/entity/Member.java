package com.ott.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Member {
	
	@Id
	private String id;       		//���̵�
	
	private String password;		//��й�ȣ
	private String name;			//�̸�
	private String phoneNumber;		//��ȭ��ȣ
	private String position;		//ȸ������
	private String email;			//�̸���
	private Date reg_date;			//���Գ�¥
	private Date renew_date;		//�̿�ǰ��ų�¥
	private Date end_date;			//�̿�����ᳯ¥
	
	@OneToMany(mappedBy = "member")
	private List<Board> boards = new ArrayList<>();
	
	
}
