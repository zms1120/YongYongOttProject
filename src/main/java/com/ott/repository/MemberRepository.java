package com.ott.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ott.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	Optional<Member> findById(String id); 
	
	   // 이메일과 핸드폰번호로 아이디 찾기
    @Query("SELECT m FROM Member m WHERE m.email = :email AND m.phone_number = :phone_number")
    Member findByEmailAndPhone_number(@Param("email") String email, @Param("phone_number") String phone_number);
    
    // 아이디와 이메일로 아이디 찾기
    Member findByIdAndEmail (String id, String email);   

}
