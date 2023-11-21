package com.ott.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ott.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
