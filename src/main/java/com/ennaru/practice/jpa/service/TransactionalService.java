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
    private final TransactionalSubService transactionalSubService;
    public TransactionalService(MemberRepository memberRepository,
                                TransactionalSubService transactionalSubService) {
        this.memberRepository = memberRepository;
        this.transactionalSubService = transactionalSubService;
    }

    /**
     * Propagation.REQUIRED
     */
    @Transactional
    public void requiredTest(boolean rollbackFlag) {
        memberRepository.save(new Member("회원1", "19980101"));
        getTransactionStatus("BeforeTX");
        transactionalSubService.requiredTest(rollbackFlag);
    }

    /**
     * Propagation.SUPPORTS
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void supportsTest(String rollbackYn) {
        getTransactionStatus("BeforeTX");
        memberRepository.save(new Member("회원1", "19950101"));
        transactionalSubService.supportsTest();
    }

    /**
     * Propagation.MANDATORY
     * [Propagation.REQUIRED] -> [Propagation.MANDATORY]
     */
    @Transactional
    public void mandatoryTest(String rollbackYn) {
        getTransactionStatus("BeforeTX");
        memberRepository.save(new Member("휴고", "19950101"));
        mandatoryTestWithoutTransaction(rollbackYn);
    }

    /**
     * Propagation.MANDATORY
     * 실행 시 [IllegalTransactionStateException]이 발생합니다.
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatoryTestWithoutTransaction(String rollbackYn) {
        getTransactionStatus("BeforeTX");
        memberRepository.save(new Member("삼체", "19950101"));
    }

    /**
     * Propagation.REQUIRES_NEW
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNewTest(boolean rollbackFlag) {
        memberRepository.save(new Member("멤버1", "19950104"));
        getTransactionStatus("BeforeTX");
        try {
            transactionalSubService.requiresNewTest(rollbackFlag);
        } catch(Exception e) {
            // 이곳에서 예외 전파가 이뤄지면 해당 메소드에서 발생한 트랜잭션도 롤백됩니다.
            log.error(e.getMessage());
        }

    }

    /**
     * Propagation.NEVER
     * 실행 시 [IllegalTransactionStateException]이 발생합니다.
     */
    @Transactional
    public void neverTest(String rollbackYn) {
        getTransactionStatus("BeforeTX");
        memberRepository.save(new Member("포카리", "19950101"));
        transactionalSubService.neverTest();
    }

    /**
     * Propagation.NEVER
     */
    @Transactional(propagation = Propagation.NEVER)
    public void neverTestWithoutTransaction(String rollbackYn) {
        getTransactionStatus("BeforeTX");
        memberRepository.save(new Member("삼체", "19950101"));
    }

    public void getTransactionStatus(String txStatus) {
        log.info("[TX_NAME]\t{}", TransactionSynchronizationManager.getCurrentTransactionName());
        getMemberList(txStatus);
    }

    public void getMemberList(String prefix) {
        memberRepository.findAll().forEach((el) -> {
            log.info(String.format("%s\t%s", prefix, el.toString()));
        });
    }
}
