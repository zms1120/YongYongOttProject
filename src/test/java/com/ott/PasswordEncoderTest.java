package com.ott;

import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ott.entity.Member;
import com.ott.repository.MemberRepository;





@SpringBootTest
public class PasswordEncoderTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	/*
	 * 테스트 데이터 insert
	 */
	@Disabled
	@Test
	public void testInsert() {
		 Member member1 = Member.builder()
		            .id("admin")
		            .password(encoder.encode("1234"))
		            .name("관리자")
		            .position("ADMIN")
		            .age(33)
		            .email("admin@email.com")
		            .phone_number("000-0000-0000")
		            .reg_date(new Date())
		            .build();
		      
		      memberRepository.save(member1);
	}
}
