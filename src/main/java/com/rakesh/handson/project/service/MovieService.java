package com.rakesh.handson.project.service;

import com.rakesh.handson.project.contract.MovieDto;
import com.rakesh.handson.project.exception.MovieNotFoundException;
import com.rakesh.handson.project.model.Movie;
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

    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
    }

    public MovieDto getMovieById(int id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> {
            log.error("Movie with id: {} not found", id);
            return new MovieNotFoundException(id);
        });
        return modelMapper.map(movie, MovieDto.class);
    }
    public List<MovieDto> getMoviesByStatus(String status) {
        List<Movie> movies = movieRepository.findByStatus(status);
        return movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
    }

    public MovieDto addMovie(MovieDto movieDto) {
        Movie movieEntity = modelMapper.map(movieDto, Movie.class);
        Movie savedMovie = movieRepository.save(movieEntity);
        return modelMapper.map(savedMovie, MovieDto.class);
    }

    public MovieDto updateMovieById(int id, MovieDto movieDto) {
        Movie existingMovie = movieRepository.findById(id).orElseThrow(() -> {
            log.error("Movie with id: {} not found", id);
            return new MovieNotFoundException(id);
        });

        modelMapper.map(movieDto, existingMovie);
        Movie updatedMovie = movieRepository.save(existingMovie);
        return modelMapper.map(updatedMovie, MovieDto.class);
    }

    public void deleteMovieById(int id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException(id);
        }
        movieRepository.deleteById(id);
    }

}
