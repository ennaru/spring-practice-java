package com.ennaru.practice.jpa.domain;

import com.ennaru.practice.jpa.enums.Authority;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Member Object (without Lombok)
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
