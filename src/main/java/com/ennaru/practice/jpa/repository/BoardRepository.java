package com.ennaru.practice.jpa.repository;

import com.ennaru.practice.jpa.dto.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {

}