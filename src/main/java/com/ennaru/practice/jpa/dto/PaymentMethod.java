package com.ennaru.practice.jpa.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.Mapping;

import java.sql.Timestamp;

/**
 * MappedSuperClass 어노테이션 부착 시 상속 관계를 생성할 수 있습니다.
 */
@MappedSuperclass
@Data
public abstract class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp registerDate;

}

