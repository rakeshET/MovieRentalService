package com.rakesh.handson.project.repository;

import com.rakesh.handson.project.dto.MovieRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieRequest, Integer> {
    Optional<MovieRequest> findById(int id);
}
