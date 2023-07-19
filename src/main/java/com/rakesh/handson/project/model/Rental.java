package com.rakesh.handson.project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "rentals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Size(min = 1, message = "Rented movie-id  should be provided")
    private String movieId;

    @Size(min = 1, message = "Rented Person id should be provided")
    private String userId;

    @NotNull(message = "Rental date should be provided")
    @PastOrPresent(message = "Rental date should be in the past or present")
    private LocalDate rentalDate;

    @NotNull(message = "Return date should be provided")
    @FutureOrPresent(message = "Return date should be in the present or future")
    private LocalDate returnDate;

}



