package com.rakesh.handson.project.contract;

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
