package com.rakesh.handson.project.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.Year;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String title;

    private String genre;

    private Year releaseYear;

    private String status;


}



