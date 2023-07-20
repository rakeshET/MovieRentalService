package com.rakesh.handson.project.contract;

import com.rakesh.handson.project.validation.movies.ValidGenre;
import com.rakesh.handson.project.validation.movies.ValidStatus;
import com.rakesh.handson.project.validation.movies.ValidTitle;
import com.rakesh.handson.project.validation.movies.ValidYear;
import java.time.Year;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private int id;

    @ValidTitle private String title;

    @ValidGenre private String genre;

    @ValidYear private Year releaseYear;

    @ValidStatus private String status;

    public enum MovieStatus {
        Available,
        Rented
    }
}
