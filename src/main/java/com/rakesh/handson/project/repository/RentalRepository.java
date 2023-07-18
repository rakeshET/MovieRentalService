package com.rakesh.handson.project.repository;

import com.rakesh.handson.project.dto.RentalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<RentalRequest, Integer> {
    Optional<RentalRequest> findById(int id);
}
