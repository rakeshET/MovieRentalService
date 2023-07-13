package com.rakesh.handson.project.Repo;

import com.rakesh.handson.project.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {
    Optional<Movie> findById(int id);
}
