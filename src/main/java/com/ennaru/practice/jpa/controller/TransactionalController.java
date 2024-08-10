package com.ennaru.practice.jpa.controller;

import com.ennaru.practice.common.domain.BaseResponse;
import com.ennaru.practice.jpa.service.TransactionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
@Tag(name = "Transactional.Propagation")
public class TransactionalController {

    private final TransactionalService transactionalService;
    public TransactionalController(
            TransactionalService transactionalService) {
        this.transactionalService = transactionalService;
    }

    @GetMapping("/transactional/required")
    @Operation(summary = "Propagation.REQUIRED")
    public BaseResponse req() {
        transactionalService.requiredTest();

        return BaseResponse.builder()
                .result_code("0")
                .result_message("성공")
                .build();
    }

    @GetMapping("/transactional/supports")
    @Operation(summary = "Propagation.SUPPORTS")
    public BaseResponse supports() {
        transactionalService.supportsTest();

        return BaseResponse.builder()
                .result_code("0")
                .result_message("성공")
                .build();
    }

    @Parameter(name = "newTransaction", description = "[Y]로 보내면 새 트랜잭션을 생성한 후 Propagation.Mandatory 트랜잭션을 실행합니다.")
    @GetMapping("/transactional/mandatory")
    @Operation(summary = "Propagation.MANDATORY")
    public BaseResponse mandatory(@RequestParam(required = false, defaultValue = "") String newTransaction) {
        try {
            if("Y".equals(newTransaction.toUpperCase(Locale.ROOT))) {
                transactionalService.mandatoryTest();
            } else {
                transactionalService.mandatoryTestWithoutTransaction();
            }
        } catch (Exception e) {
            // [Propagation.MANDATORY] 는 transaction이 없는 상태에서 실행하면 [IllegalTransactionStateException]이 발생합니다.
            return BaseResponse.builder()
                    .result_code("1")
                    .result_message("실패하였습니다. (result_data를 확인하세요.)")
                    .result_data(String.format("[%s]: %s", e.getClass().getSimpleName(), e.getMessage()))
                    .build();
        }

        return BaseResponse.builder()
                .result_code("0")
                .result_message("성공")
                .build();
    }

    @GetMapping("/transactional/requiresNew")
    @Operation(summary = "Propagation.REQUIRES_NEW")
    public BaseResponse requiresNew() {
        transactionalService.requiresNewTest();

        return BaseResponse.builder()
                .result_code("0")
                .result_message("성공")
                .build();
    }

    @Parameter(name = "newTransaction", description = "[Y]로 보내면 새 트랜잭션을 생성한 후 Propagation.NEVER 트랜잭션을 실행합니다.")
    @GetMapping("/transactional/never")
    @Operation(summary = "Propagation.NEVER")
    public BaseResponse never(@RequestParam(required = false, defaultValue = "") String newTransaction) {
        try {
            if("Y".equals(newTransaction.toUpperCase(Locale.ROOT))) {
                transactionalService.neverTest();
            } else {
                transactionalService.neverTestWithoutTransaction();
            }
        } catch (Exception e) {
            // [Propagation.NEVER] 는 transaction이 존재한 상태에서 실행하면 [IllegalTransactionStateException]이 발생합니다.
            return BaseResponse.builder()
                    .result_code("1")
                    .result_message("실패하였습니다. (result_data를 확인하세요.)")
                    .result_data(String.format("[%s]: %s", e.getClass().getSimpleName(), e.getMessage()))
                    .build();
        }

        return BaseResponse.builder()
                .result_code("0")
                .result_message("성공")
                .build();
    }


}
