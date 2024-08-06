package com.ennaru.practice.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Member Object (without Lombok)
 */
@Entity
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long memberId;
    private String memberName;
    private String registerDate;

    public Member(String memberName, String registerDate) {
        this.memberName = memberName;
        this.registerDate = registerDate;
    }

}
