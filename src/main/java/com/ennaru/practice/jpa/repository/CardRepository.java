package com.ennaru.practice.jpa.repository;

import com.ennaru.practice.jpa.dto.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

}