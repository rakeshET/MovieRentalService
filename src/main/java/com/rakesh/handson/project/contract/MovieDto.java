package com.rakesh.handson.project.contract;

import com.rakesh.handson.project.validation.movies.ValidStatus;
import com.rakesh.handson.project.validation.movies.ValidTitle;
import com.rakesh.handson.project.validation.movies.ValidYear;
import com.rakesh.handson.project.validation.movies.ValidGenre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Year;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private int id;

    @ValidTitle
    private String title;

    @ValidGenre
    private String genre;

    @ValidYear
    private Year releaseYear;

    @ValidStatus // Use the custom constraint for status
    private String status;

    public enum MovieStatus {
        Available,
        Rented
    }
}

