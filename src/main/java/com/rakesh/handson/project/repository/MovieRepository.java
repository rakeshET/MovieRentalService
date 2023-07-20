package com.rakesh.handson.project.repository;

import com.rakesh.handson.project.model.Movie;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findById(int id);

    List<Movie> findByStatus(String status);
}
