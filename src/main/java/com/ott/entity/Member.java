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
	private String id;       		//아이디
	
	private String password;		//비밀번호
	private String name;			//이름
	private String phoneNumber;		//전화번호
	private String position;		//회원구분
	private String email;			//이메일
	private Date reg_date;			//가입날짜
	private Date renew_date;		//이용권갱신날짜
	private Date end_date;			//이용권종료날짜
	
	@OneToMany(mappedBy = "member")
	private List<Board> boards = new ArrayList<>();
	
	
}
