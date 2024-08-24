package com.ennaru.practice.jpa.dto;

import com.ennaru.practice.jpa.enums.Authority;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Member Object
 * <br/>
 * 해당 엔티티는 member, member_authority 테이블을 생성합니다.
 */
@Entity
@Data
@SecondaryTable(name = "member_authority",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "member_id"))
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long memberId;
    private String memberName;
    private String registerDate;

    @Column(table = "member_authority", name="member_authority")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public Member(String memberName, String registerDate) {
        this.memberName = memberName;
        this.registerDate = registerDate;
    }

    public Member(String memberName, String registerDate, Authority authority) {
        this.memberName = memberName;
        this.registerDate = registerDate;
        this.authority = authority;
    }
}
