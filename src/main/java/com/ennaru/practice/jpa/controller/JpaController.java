package com.ennaru.practice.jpa.controller;

import com.ennaru.practice.common.domain.BaseResponse;
import com.ennaru.practice.jpa.domain.Member;
import com.ennaru.practice.jpa.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JpaController {

    private MemberRepository memberRepository;
    public JpaController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @RequestMapping("/jpa/member/call")
    public BaseResponse memberCall() {

        // data set
        memberRepository.save(new Member("둘리", "20200101"));
        memberRepository.save(new Member("도우너", "20210525"));
        memberRepository.save(new Member("고길동", "1990406"));

        memberRepository.findAll().forEach((el) -> {
            System.out.println(el.toString());
        });

        return BaseResponse.builder()
                .result_code("0")
                .result_message("성공")
                .build();
    }
}
