package com.rakesh.handson.project.dto;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Rentals")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class RentalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private int movieId;
    private String userId;
    private LocalDate rentalDate;
    private LocalDate returnDate;

}



