package com.rakesh.handson.project.controller;

import com.rakesh.handson.project.contract.MovieDto;
import com.rakesh.handson.project.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable int id) {
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<MovieDto>> getMoviesByStatus(@PathVariable("status") String status) {
        List<MovieDto> moviesByStatus = movieService.getMoviesByStatus(status);
        return new ResponseEntity<>(moviesByStatus, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovieDto> addMovie(@Valid @RequestBody MovieDto movieDto) {
        MovieDto savedMovieDto = movieService.addMovie(movieDto);
        return new ResponseEntity<>(savedMovieDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovieById(@PathVariable int id, @Valid @RequestBody MovieDto movie) {
        MovieDto updatedMovie = movieService.updateMovieById(id, movie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable int id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.ok("Movie with ID " + id + " has been deleted.");
    }

}
