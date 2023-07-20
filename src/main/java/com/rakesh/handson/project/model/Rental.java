package com.rakesh.handson.project.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rentals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String movieId;

    private String userId;

    private LocalDate rentalDate;

    private LocalDate returnDate;
}
