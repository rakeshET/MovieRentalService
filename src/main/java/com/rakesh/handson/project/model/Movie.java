package com.rakesh.handson.project.model;

import jakarta.persistence.*;
import java.time.Year;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
