package com.alternova.streaming.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alternova.streaming.persistence.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
