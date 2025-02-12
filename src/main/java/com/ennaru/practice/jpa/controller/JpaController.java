package com.ennaru.practice.jpa.controller;

import com.ennaru.practice.common.domain.BaseResponse;
import com.ennaru.practice.jpa.dto.*;
import com.ennaru.practice.jpa.enums.Authority;
import com.ennaru.practice.jpa.repository.*;
import com.ennaru.practice.jpa.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@RestController
public class JpaController {

    private final MemberService memberService;
    private final BoardRepository boardRepository;
    private final AccessLogRepository accessLogRepository;
    public JpaController(MemberService memberService,
                         BoardRepository boardRepository,
                         AccessLogRepository accessLogRepository) {
        this.memberService = memberService;
        this.boardRepository = boardRepository;
        this.accessLogRepository = accessLogRepository;
    }

    @RequestMapping("/jpa/member/register")
    public BaseResponse memberRegister() {

        // save transaction
        memberService.save(new Member("돌리", LocalDate.now().toString(), Authority.USER));

        // find transaction
        for (Member member : memberService.findAll()) {
            log.info(member.toString());
        }

        return BaseResponse.builder()
                .result_code("0")
                .result_message("성공")
                .build();
    }

    @RequestMapping("/jpa/member/post")
    public BaseResponse post(@RequestBody Board board) {

        // 게시글 작성
        try {
            if(board.getMemberId() == null) {
                throw new IllegalArgumentException("회원을 확인할 수 없습니다");
            }
            if(ObjectUtils.isEmpty(board.getTitle())) {
                throw new IllegalArgumentException("게시글 제목은 생략할 수 없습니다.");
            }
            if(ObjectUtils.isEmpty(board.getContent())) {
                throw new IllegalArgumentException("게시글 내용은 생략할 수 없습니다.");
            }

            boardRepository.save(board);

        } catch(Exception e) {
            return BaseResponse.builder()
                    .result_code("-1")
                    .result_message("게시글 작성에 실패하였습니다. 아래 로그를 확인해 주세요.")
                    .result_data(e.getMessage())
                    .build();
        }

        return BaseResponse.builder()
                .result_code("0")
                .result_message("성공")
                .result_data(boardRepository.findById(board.getId()).toString())
                .build();
    }

    @RequestMapping("/jpa/member/call")
    public BaseResponse memberCall() {

        // today
        LocalDateTime localDate = LocalDateTime.now();

        // data set
        memberService.save(new Member("둘리", "20200101"));
        memberService.save(new Member("도우너", "20210525"));
        memberService.save(new Member("고길동", "1990406"));

        // iterator 반복문 표현 1
//        memberService.findAll().forEach((el) -> {
//
//            // 첫 번째 회원이 게시글 작성
//            if(el.getMemberId() == 1) {
//                Board board = Board.builder()
//                        .memberId(el.getMemberId())
//                        .title("최초 게시글")
//                        .content("첫 번째 게시물입니다.")
//                        .registerDate(localDate.toString())
//                        .build();
//                boardRepository.save(board);
//            }
//
//            accessLogRepository.save(new AccessLog(el.getMemberId(), localDate.toString()));
//        });

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
