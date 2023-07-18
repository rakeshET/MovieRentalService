package com.rakesh.handson.project.controller;

import com.rakesh.handson.project.dto.MovieResponse;
import com.rakesh.handson.project.dto.MovieRequest;
import com.rakesh.handson.project.service.MovieService;
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
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable int id) {
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovieResponse> addMovie(@RequestBody MovieRequest movie) {
        MovieResponse movieResponse = movieService.addMovie(movie);
        return new ResponseEntity<>(movieResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovieById(@PathVariable int id, @RequestBody MovieRequest movie) {
        MovieResponse updatedMovie = movieService.updateMovieById(id, movie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable int id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.ok("MovieRequest with ID " + id + " has been deleted.");
    }

}
