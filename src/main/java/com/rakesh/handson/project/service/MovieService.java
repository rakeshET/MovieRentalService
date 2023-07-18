package com.rakesh.handson.project.service;

import com.rakesh.handson.project.dto.MovieRequest;
import com.rakesh.handson.project.dto.MovieResponse;
import com.rakesh.handson.project.exception.MovieNotFoundException;
import com.rakesh.handson.project.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MovieService(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    public List<MovieResponse> getAllMovies() {
        List<MovieRequest> movies = movieRepository.findAll();
        return movies.stream()
                .map(movie -> modelMapper.map(movie, MovieResponse.class))
                .collect(Collectors.toList());
    }

    public MovieResponse getMovieById(int id) {
        MovieRequest movie = movieRepository.findById(id).orElseThrow(() -> {
            log.error("MovieRequest with id: {} not found", id);
            return new MovieNotFoundException(id);
        });
        return modelMapper.map(movie, MovieResponse.class);
    }

    public MovieResponse addMovie(MovieRequest movie) {
        MovieRequest savedMovie = movieRepository.save(movie);
        return modelMapper.map(savedMovie, MovieResponse.class);
    }

    public MovieResponse updateMovieById(int id, MovieRequest movie) {
        MovieRequest existingMovie = movieRepository.findById(id).orElseThrow(() -> {
            log.error("MovieRequest with id: {} not found", id);
            return new MovieNotFoundException(id);
        });

        modelMapper.map(movie, existingMovie);
        MovieRequest updatedMovie = movieRepository.save(existingMovie);
        return modelMapper.map(updatedMovie, MovieResponse.class);
    }

    public void deleteMovieById(int id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException(id);
        }
        movieRepository.deleteById(id);
    }
}
