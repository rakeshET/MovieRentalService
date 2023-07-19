package com.rakesh.handson.project.contract;

import lombok.*;

import java.time.Year;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MovieDto {
    private int id;
    private String title;
    private String genre;
    private Year releaseYear;
    private MovieStatus status;

    public enum MovieStatus {
        Available,
        Rented
    }

}

