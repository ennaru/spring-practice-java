package com.ennaru.practice.jpa.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long memberId;
    private String title;
    private String content;
    private String registerDate;

    @Builder
    public Board(Long memberId, String title, String content) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
    }

    @PrePersist
    public void perPersist() {
        this.registerDate = LocalDate.now().toString();
    }
}
