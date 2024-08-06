package com.ennaru.practice.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;
    private long memberId;
    private String accessDate;

    public AccessLog(long memberId, String accessDate) {
        this.memberId = memberId;
        this.accessDate = accessDate;
    }
}
