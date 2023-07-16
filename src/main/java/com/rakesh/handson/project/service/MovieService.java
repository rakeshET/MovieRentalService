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
        for (Movie movie : movies) {
            responses.add(
                    MovieResponse
                            .builder()
                            .id(movie.getId())
                            .title(movie.getTitle())
                            .genre(movie.getGenre())
                            .releaseYear(movie.getReleaseYear())
                            .status(movie.getStatus())
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


    public MovieResponse addMovie(Movie movie) {
        movie.setTitle(movie.getTitle());
        movie.setGenre(movie.getGenre());
        movie.setReleaseYear(movie.getReleaseYear());
        movie.setStatus(movie.getStatus());

        Movie savedMovie = movieRepository.save(movie);

        return MovieResponse.builder()
                .id(savedMovie.getId())
                .title(savedMovie.getTitle())
                .genre(savedMovie.getGenre())
                .releaseYear(savedMovie.getReleaseYear())
                .status(savedMovie.getStatus())
                .build();
    }
    public MovieResponse updateMovieById(int id, Movie movie) {
        Movie existingMovie = movieRepository.findById(id).orElseThrow(() -> {
            log.error("Movie with id: {} not found", id);
            return new MovieNotFoundException(id);
        });

        existingMovie.setTitle(movie.getTitle());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setReleaseYear(movie.getReleaseYear());
        existingMovie.setStatus(movie.getStatus());

        Movie updatedMovie = movieRepository.save(existingMovie);

        return MovieResponse.builder()
                .id(updatedMovie.getId())
                .title(updatedMovie.getTitle())
                .genre(updatedMovie.getGenre())
                .releaseYear(updatedMovie.getReleaseYear())
                .status(updatedMovie.getStatus())
                .build();
    }
    public void deleteMovieById(int id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException(id);
        }
        movieRepository.deleteById(id);
    }
}
