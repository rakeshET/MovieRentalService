package com.rakesh.handson.project.model;


import jakarta.persistence.*;
import lombok.*;

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
    private String title;
    private String genre;
    private int releaseYear;
    private String status;

}



