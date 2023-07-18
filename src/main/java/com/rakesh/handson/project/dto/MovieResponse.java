package com.rakesh.handson.project.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {
    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private String status;
}
