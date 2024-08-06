package com.ennaru.practice.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private String registerDate;

    @Builder
    public Board(Long memberId, String title, String content, String registerDate) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.registerDate = registerDate;
    }
}
