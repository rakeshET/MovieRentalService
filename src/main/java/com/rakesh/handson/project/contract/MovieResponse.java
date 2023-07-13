package com.rakesh.handson.project.contract;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MovieResponse {
    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private String status;
}
