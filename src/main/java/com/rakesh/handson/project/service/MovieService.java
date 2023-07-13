package com.rakesh.handson.project.service;

import com.rakesh.handson.project.contract.MovieResponse;
import com.rakesh.handson.project.exception.MovieNotFoundException;
import com.rakesh.handson.project.model.Movie;
import com.rakesh.handson.project.repository.MovieRepository;
import com.rakesh.handson.project.repository.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MovieService {
    private final MovieRepository movieRepository;
    private final RentalRepository rentalRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, RentalRepository rentalRepository) {
        this.movieRepository = movieRepository;
        this.rentalRepository = rentalRepository;
    }


    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = this.movieRepository.findAll();
        List<MovieResponse> responses = new ArrayList<>();
        for (Movie m : movies) {
            responses.add(
                    MovieResponse
                            .builder()
                            .id(m.getId())
                            .title(m.getTitle())
                            .genre(m.getGenre())
                            .releaseYear(m.getReleaseYear())
                            .status(m.getStatus())
                            .build()
            );
        }
        return responses;
    }


    public MovieResponse getMovieById(int id) {
        Movie movie = this.movieRepository.findById(id).orElseThrow(() -> {
            log.error("Movie with id: {} not found", id);
            return new MovieNotFoundException(id);
        });
        return MovieResponse
                .builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .releaseYear(movie.getReleaseYear())
                .status(movie.getStatus())
                .build();
    }

}
