package com.ennaru.practice.jpa.repository;

import com.ennaru.practice.jpa.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {

    List<Member> findByMemberName(String memberName);

    Member findByMemberId(long memberId);

}
