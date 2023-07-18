package com.rakesh.handson.project.repository;

import com.rakesh.handson.project.contract.MovieDto;
import com.rakesh.handson.project.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findById(int id);
}
