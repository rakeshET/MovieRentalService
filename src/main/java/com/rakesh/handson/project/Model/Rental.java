package com.rakesh.handson.project.Model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name ="Rentals")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

    public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int movieId;
    private String userId;
    private LocalDate rentalDate;
    private LocalDate returnDate;

}



