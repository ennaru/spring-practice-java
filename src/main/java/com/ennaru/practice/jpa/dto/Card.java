package com.ennaru.practice.jpa.dto;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

/**
 * AttributeOverride 사용 시 상위 클래스에서 컬럼을 매핑할 수 있습니다.
 */
@Entity
@AttributeOverride(name = "id", column = @Column(name = "card_id"))
public class Card extends PaymentMethod {
    private String cardNumber;
}
