package com.ennaru.practice.jpa.service;

import com.ennaru.practice.jpa.domain.Member;
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
    @Transactional
    public void requiredTest() {
        getTransactionStatus();
        memberRepository.save(new Member("회원2", "19980102"));
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
    public void requiresNewTest() {
        getTransactionStatus();
        memberRepository.save(new Member("멤버1", "19950104"));
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
        log.info("TX_NAME\t{}", TransactionSynchronizationManager.getCurrentTransactionName());
    }

    public void getMemberList() {
        this.getMemberList("");
    }

    public void getMemberList(String prefix) {
        log.info("[{}]", prefix);
        memberRepository.findAll().forEach((el) -> {
            log.info(el.toString());
        });
    }
}
