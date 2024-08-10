package com.ennaru.practice.jpa.controller;

import com.ennaru.practice.common.domain.BaseResponse;
import com.ennaru.practice.jpa.service.TransactionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TransactionalController {

    private final TransactionalService transactionalService;
    public TransactionalController(
            TransactionalService transactionalService) {
        this.transactionalService = transactionalService;
    }

    @GetMapping("/transactional/required")
    public BaseResponse req() {
        transactionalService.requiredTest();

        return BaseResponse.builder()
                .result_code("0")
                .result_message("성공")
                .build();
    }

    @GetMapping("/transactional/supports")
    public BaseResponse supports() {
        transactionalService.supportsTest();

        return BaseResponse.builder()
                .result_code("0")
                .result_message("성공")
                .build();
    }

    @GetMapping("/transactional/mandatory")
    public BaseResponse mandatory() {
        try {
            transactionalService.mandatoryTest();
        } catch (Exception e) {
            // [Propagation.MANDATORY] 는 transaction 이 없는 상태에서 실행하면 에러가 발생합니다.
            return BaseResponse.builder()
                    .result_code("1")
                    .result_message("실패하였습니다. (result_data를 확인하세요.)")
                    .result_data(e.getMessage())
                    .build();
        }

        return BaseResponse.builder()
                .result_code("0")
                .result_message("성공")
                .build();
    }


}
