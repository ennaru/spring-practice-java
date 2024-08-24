package com.ennaru.practice.jpa.service;

import com.ennaru.practice.jpa.dto.Member;
import com.ennaru.practice.jpa.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Iterable<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public void save(Member member) {
        memberRepository.save(member);
    }
}
