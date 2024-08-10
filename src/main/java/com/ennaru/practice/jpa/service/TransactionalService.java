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
public class TransactionalService {

    private final MemberRepository memberRepository;
    public TransactionalService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Propagation.REQUIRED
     */
    @Transactional
    public void requiredTest() {
        getTransactionName();
        memberRepository.save(new Member("회원1", "19980101"));
        requiredTest2();
    }

    /**
     * Propagation.REQUIRED (new transaction)
     */
    @Transactional
    private void requiredTest2() {
        getTransactionName();
        memberRepository.save(new Member("회원2", "19980102"));
    }

    /**
     * Propagation.SUPPORTS
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void supportsTest() {
        getTransactionName();
        memberRepository.save(new Member("회원1", "19950101"));
        supportsTest2();
    }

    @Transactional
    private void supportsTest2() {
        getTransactionName();
        memberRepository.save(new Member("회원2", "19950102"));
    }

    /**
     * Propagation.MANDATORY
     * 실행 시 [IllegalTransactionStateException]이 발생합니다.
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatoryTest() {
        getTransactionName();
        memberRepository.save(new Member("삼체", "19950101"));
    }

    /**
     * Propagation.MANDATORY
     */
    @Transactional
    public void mandatoryTest2() {
        getTransactionName();
        memberRepository.save(new Member("휴고", "19950101"));
        mandatoryTest();
    }

    public void getTransactionName() {
        log.info("{}", TransactionSynchronizationManager.getCurrentTransactionName());
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
