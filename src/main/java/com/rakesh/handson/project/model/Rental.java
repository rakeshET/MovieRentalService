package com.rakesh.handson.project.model;


import jakarta.persistence.*;
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
    private int movieId;
    private String userId;
    private LocalDate rentalDate;
    private LocalDate returnDate;

}



