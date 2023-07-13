package com.rakesh.handson.project.Repo;

import com.rakesh.handson.project.Model.Movie;
import com.rakesh.handson.project.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalRepo extends JpaRepository<Rental, Integer> {
    Optional<Rental> findById(int id);
}
