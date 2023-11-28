package com.ott.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ott.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {

	

}
