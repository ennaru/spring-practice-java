package com.ennaru.practice.jpa.controller;

import com.ennaru.practice.common.domain.BaseResponse;
import com.ennaru.practice.jpa.domain.AccessLog;
import com.ennaru.practice.jpa.domain.Board;
import com.ennaru.practice.jpa.domain.Member;
import com.ennaru.practice.jpa.repository.AccessLogRepository;
import com.ennaru.practice.jpa.repository.BoardRepository;
import com.ennaru.practice.jpa.repository.MemberRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class JpaController {

    private MemberRepository memberRepository;
    private BoardRepository boardRepository;
    private AccessLogRepository accessLogRepository;
    public JpaController(MemberRepository memberRepository,
                         BoardRepository boardRepository,
                         AccessLogRepository accessLogRepository) {
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
        this.accessLogRepository = accessLogRepository;
    }

    @RequestMapping("/jpa/member/call")
    public BaseResponse memberCall() {

        // today
        LocalDateTime localDate = LocalDateTime.now();

        // data set
        memberRepository.save(new Member("둘리", "20200101"));
        memberRepository.save(new Member("도우너", "20210525"));
        memberRepository.save(new Member("고길동", "1990406"));

        // iterator 반복문 표현 1
        memberRepository.findAll().forEach((el) -> {

            // 첫 번째 회원이 게시글 작성
            if(el.getMemberId() == 1) {
                Board board = Board.builder()
                        .memberId(el.getMemberId())
                        .title("최초 게시글")
                        .content("첫 번째 게시물입니다.")
                        .registerDate(localDate.toString())
                        .build();
                boardRepository.save(board);
            }

            accessLogRepository.save(new AccessLog(el.getMemberId(), localDate.toString()));
        });

        boardRepository.findAll().forEach((el) -> {
            System.out.println(el.toString());
        });


        // iterator 반복문 표현 2
        for (AccessLog accessLog : accessLogRepository.findAll()) {
            System.out.println(accessLog.toString());
        }

        return BaseResponse.builder()
                .result_code("0")
                .result_message("성공")
                .build();
    }

}
