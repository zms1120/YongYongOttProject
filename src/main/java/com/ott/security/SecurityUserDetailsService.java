package com.ott.security;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ott.entity.Member;
import com.ott.repository.MemberRepository;



/*
 * MemberRepository로 회원정보를 조회하여
 * UserDetails 객체(spring Security에서 사용하기위함)로 변환
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // MemberRepository에서 회원정보 조회
        Optional<Member> result = memberRepository.findById(username);

        if (result.isPresent()) {
            Member member = result.get();

            // UserDetails 타입 객체로 변환
            return new SecurityUser(member);

        } else {
            throw new UsernameNotFoundException(username + ": 사용자가 존재하지 않습니다.");
        }
    }
}

