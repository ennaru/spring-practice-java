package com.ennaru.practice.jpa.repository;

import com.ennaru.practice.jpa.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
}
