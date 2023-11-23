package com.ott.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ott.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
