package com.rakesh.handson.project.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Movies")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private String status;


}



