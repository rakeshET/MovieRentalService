package com.rakesh.handson.project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Year;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Size(min = 1, message = "Movie name should be provided")
    private String title;

    @NotEmpty(message = "Genre should be provided")
    private String genre;

    @PastOrPresent(message = "Release year should be in the past or present")
    private Year releaseYear;

    @NotEmpty(message = "Status should be provided AS Available or Rented")
    private String status;


}



