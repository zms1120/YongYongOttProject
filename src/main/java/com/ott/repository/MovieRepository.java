package com.ott.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ott.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {

}
