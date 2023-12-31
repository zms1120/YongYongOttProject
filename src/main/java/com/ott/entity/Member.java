package com.ott.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "boards")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	
	@Id
	private String id;       		//아이디
	private String password;		//비밀번호
	private String name;			//이름
	private String phone_number;		//전화번호
	private String position;		//회원구분
	private String email;			//이메일
	private int age;				//나이
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reg_date;			//가입날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date renew_date;		//이용권갱신날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end_date;			//이용권종료날짜
	
	
	

	public List<Board> getBoards() {
		return boards;
	}


	@OneToMany(fetch = FetchType.EAGER, mappedBy = "member")
	@JsonIgnore
	private List<Board> boards = new ArrayList<>();
	
	
}
