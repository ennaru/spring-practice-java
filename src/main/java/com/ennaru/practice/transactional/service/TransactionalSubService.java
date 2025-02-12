package com.ennaru.practice.transactional.service;

import com.ennaru.practice.jpa.dto.Member;
import com.ennaru.practice.jpa.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Service
public class TransactionalSubService {

    private final MemberRepository memberRepository;
    public TransactionalSubService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Propagation.REQUIRED (new transaction)
     */
    @Transactional(rollbackFor = Exception.class)
    public void requiredTest(boolean rollbackFlag) {
        getTransactionStatus();
        memberRepository.save(new Member("회원2", "19980102"));
        if(rollbackFlag) {
            throw new RuntimeException("롤백 테스트를 위해 Exception을 발생시켰습니다.");
        }
    }

    /**
     * Propagation.SUPPORTS
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void supportsTest() {
        getTransactionStatus();
        memberRepository.save(new Member("회원1", "19950101"));
    }

    /**
     * Propagation.MANDATORY
     * [Propagation.REQUIRED] -> [Propagation.MANDATORY]
     */
    @Transactional
    public void mandatoryTest() {
        getTransactionStatus();
        memberRepository.save(new Member("휴고", "19950101"));
        mandatoryTestWithoutTransaction();
    }

    /**
     * Propagation.MANDATORY
     * 실행 시 [IllegalTransactionStateException]이 발생합니다.
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatoryTestWithoutTransaction() {
        getTransactionStatus();
        memberRepository.save(new Member("삼체", "19950101"));
    }

    /**
     * Propagation.REQUIRES_NEW
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNewTest(boolean rollbackFlag) {
        getTransactionStatus();
        memberRepository.save(new Member("멤버1", "19950104"));
        if(rollbackFlag) {
            throw new RuntimeException("롤백 테스트를 위해 Exception을 발생시켰습니다.");
        }
    }

    /**
     * Propagation.NEVER
     * [Propagation.REQUIRED] -> [Propagation.NEVER]
     * 실행 시 [IllegalTransactionStateException]이 발생합니다.
     */
    @Transactional(propagation = Propagation.NEVER)
    public void neverTest() {
        getTransactionStatus();
        memberRepository.save(new Member("휴고", "19950101"));
    }

    public void getTransactionStatus() {
        log.info("[TX_NAME]\t{}", TransactionSynchronizationManager.getCurrentTransactionName());
    }

    public void getMemberList(String prefix) {
        memberRepository.findAll().forEach((el) -> {
            log.info("[{}]\t{}", prefix, el.toString());
        });
    }
}
