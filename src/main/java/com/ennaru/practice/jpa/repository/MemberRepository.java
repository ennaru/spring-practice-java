package com.ennaru.practice.jpa.repository;

import com.ennaru.practice.jpa.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String> {

    List<Member> findByMember_name(String name);

    Member findByMember_id(String member_id);

}
